package com.practise.concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

class Car{
    private final int id;
    private boolean engine = false;
    private boolean driveTrain = false;
    private boolean wheels = false;

    public Car(int id) {
        this.id = id;
    }

    public Car() {
        this.id = -1;
    }

    public synchronized int getId(){
        return this.id;
    }

    public synchronized void addEngine(){
        this.engine = true;
    }

    public synchronized void addDriveTrain(){
        this.driveTrain = true;
    }

    public synchronized void addWheels (){
        this.wheels = true;
    }

    @Override
    public synchronized String toString() {
        return "Car " + id + " [" + " engine: " + engine + " driveTrain: " + driveTrain + " wheels: " + wheels + " ]";
    }
}

class CarQueue extends LinkedBlockingQueue<Car>{

}

class ChassisBuilder implements Runnable{

    private CarQueue carQueue;

    private int counter = 0;

    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(500);
                Car car = new Car(counter++);
                System.out.println("ChassisBuilder created " + car);
                carQueue.put(car);
            }
        }catch (InterruptedException e){
            System.out.println("Interrupted: ChassisBuilder");
        }

        System.out.println("ChassisBuilder off");
    }
}


class Assembler implements Runnable{
    private CarQueue chassisQUeue;
    private CarQueue finishingQueue;

    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue chassisQUeue, CarQueue finishingQueue, RobotPool robotPool) {
        this.chassisQUeue = chassisQUeue;
        this.finishingQueue = finishingQueue;
        this.robotPool = robotPool;
    }

    public Car car() {
        return car;
    }

    public CyclicBarrier barrier(){
        return barrier;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                car = chassisQUeue.take();
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                barrier.await();
                finishingQueue.put(car);
            }
        }catch (InterruptedException e){
            System.out.println("Exiting Assembler via interrupt");
        }catch (BrokenBarrierException e){
            throw new RuntimeException(e);
        }
        System.out.println("Assembler off");
    }
}



class Reporter implements Runnable{
    private CarQueue carQueue;

    public Reporter(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println(carQueue.take());
            }
        }catch (InterruptedException e){
            System.out.println("Exiting Reporter via interrupt");
        }
        System.out.println("Reporter off");
    }
}

abstract class Robot implements Runnable{
    private RobotPool pool;

    public Robot(RobotPool pool) {
        this.pool = pool;
    }

    protected Assembler assembler;

    public Robot assignAssembler(Assembler assembler){
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false;

     public synchronized void engage(){
         this.engage = true;
         notifyAll();
     }

     abstract protected  void performService();

    @Override
    public void run() {
        try{
            powerDown();
            while (!Thread.interrupted()){
                performService();
                assembler.barrier().await();
                powerDown();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting " + this + " via interrupt");
        }catch (BrokenBarrierException e){
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }

    private synchronized void powerDown()throws InterruptedException{
        this.engage = false;
        this.assembler = null;
        pool.release(this);
        while (engage == false){
            wait();
        }
    }

    @Override
    public String toString(){
        return getClass().getName();
    }
}

class EngineRobot extends Robot{
    public EngineRobot(RobotPool pool){
        super(pool);
    }

    @Override
    protected void performService(){
        System.out.println(this + " installing engine");
        assembler.car().addEngine();
    }
}


class DriveTrainRobot extends Robot{
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected  void performService(){
        System.out.println(this + " installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}

class WheelRobot extends Robot{

    public WheelRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        System.out.println(this + " installing Wheels");
        assembler.car().addWheels();
    }
}

class RobotPool{
    private Set<Robot> pool = new HashSet<>();

    public synchronized void add(Robot robot){
        pool.add(robot);
        notifyAll();
    }

    public synchronized void hire(Class<? extends Robot> robotType, Assembler assembler)throws InterruptedException{
        for(Robot robot: pool){
            if(robot.getClass().equals(robotType)){
                pool.remove(robot);
                robot.assignAssembler(assembler);
                robot.engage();
                return;
            }
            wait();
            hire(robotType, assembler);
        }
    }

    public synchronized void release(Robot robot){
        add(robot);
    }
}

public class CarBuilder {
    public static void main(String[] args) throws Exception{
        CarQueue chassisQueue = new CarQueue();
        CarQueue finishingQueue = new CarQueue();

        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool pool = new RobotPool();
        exec.execute(new EngineRobot(pool));
        exec.execute(new DriveTrainRobot(pool));
        exec.execute(new WheelRobot(pool));
        exec.execute(new Assembler(chassisQueue, finishingQueue, pool));
        exec.execute(new Reporter(finishingQueue));
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}

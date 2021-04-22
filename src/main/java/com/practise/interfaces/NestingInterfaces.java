package com.practise.interfaces;

// 嵌套在类中
class A{
    // 嵌套在类中的接口，访问控制权限可以是 public、private
    public interface B{
        void f();
    }

    public class BImp implements B{
        @Override
        public void f(){}
    }

    private interface  C{
        void f();
    }

    private class CImp implements C{
        @Override
        public void f(){}
    }

    public class CImp2 implements C{
        @Override
        public void f(){}
    }

    public C getC(){
        return new CImp2();
    }

    private C cRef;

    public void receiveC(C c){
        cRef = c;
        cRef.f();
    }
}

// 嵌套在接口中
interface D{
    interface  E{
        void f();
    }

    void d();

    // 在接口中不能是private
    // private interface I{}
}

// 使用嵌套接口
public class NestingInterfaces {
    public class BImp implements A.B{
        @Override
        public void f(){}
    }

//  不能实现一个private的接口，除非是在接口定义内部
//	class DImp implements A.C{
//		public void f(){}
//	}

    class DImp implements D {

        @Override
        public void d(){}
    }

    // 可以套娃
    class DImp2 implements D {
        @Override
        public void d(){}

        class DE implements D.E{
            @Override
            public void f(){}
        }
    }

    // 也可以直接实现接口中的接口
    class DEImp implements D.E{
        @Override
        public void f(){}
    }

    public static void main(String[] args){
        A a = new A();
//		无法访问到A.C
//		A.C ac = a.getc();
//		只能返回A.c
//		A.CImp2 cImp2 = a.getC();
//		不能访问接口的成员
//		a.getC().f();
//		只能新创建一个A来获取到C
        A a2 = new A();
        a2.receiveC(a.getC());
    }
}

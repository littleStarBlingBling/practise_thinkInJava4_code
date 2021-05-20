package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class RecoverCADState {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(ConstUtils.OUT_PREFIX + "CADState.out"));
        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapeList = (List<Shape>) in.readObject();
        System.out.println(shapeList);
    }
}

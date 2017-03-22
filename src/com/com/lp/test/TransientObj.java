package com.lp.test;

import java.io.Serializable;

/**
 * Created by CPR161 on 2017-03-21.
 *
 * Q：transient关键字能实现什么？

 A：当对象被序列化时（写入字节序列到目标文件）时，transient阻止实例中那些用此关键字声明的变量持久化；
 当对象被反序列化时（从源文件读取字节序列进行重构），这样的实例变量值不会被持久化和恢复。
 例如，当反序列化对象——数据流（例如，文件）可能不存在时，原因是你的对象中存在类型为java.io.InputStream的变量，序列化时这些变量引用的输入流无法被打开。
 */
public class TransientObj implements Serializable{
    private transient volatile int a ;
    private volatile int b;
    private  int c;

    public TransientObj() {
    }

    public TransientObj(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
}

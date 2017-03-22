package com.lp.test;

import com.lp.utils.SerializeUtil;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by CPR161 on 2017-03-20.
 */
public class HashTest {

    public static void main(String[] args) {
        Map hmMap = new HashMap();
        Map htMap = new Hashtable();

        hmMap.put(null,null);

        htMap.put("a","a");

        System.out.println(hmMap.size());
        System.out.println(htMap.size());


        TransientObj tranObj = new TransientObj(1,2,3);
        System.out.println("反序列前："+tranObj.getA()+","+tranObj.getB()+","+tranObj.getC());

        byte[] bytes = SerializeUtil.serialize(tranObj);

        Object obj = SerializeUtil.unserialize(bytes);
        if(obj instanceof  TransientObj){
            TransientObj tobj = (TransientObj) obj;
            System.out.println("反序列后:"+tobj.getA()+","+tobj.getB()+","+tobj.getC());
        }


        int a=0,b=0;
        int aa = a++;
        int bb = ++b;
        System.out.println(a+","+aa);
        System.out.println(b+","+bb);


    }

}

package com.mvn.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RunCallable implements Callable<Student> {
    private String nickName;
    List<Student> list;
    public RunCallable(String nickName, List<Student> list){
        this.nickName=nickName;
        this.list=list;
    }

    @Override
    public Student call() throws Exception {
        List<Student> result=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getNickname().equals(nickName)){
                    Thread.sleep(5000);
                    return list.get(i);
                }
        }
        return null;
    }
}

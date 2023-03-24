package com.mvn.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class App {
    final static Logger logger = LoggerFactory.getLogger(App.class);

    public static List<Student> generateListStudent() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1l, "Phung Duc A", "userA", "123", "userA@gmail.com"));
        list.add(new Student(2l, "Phung Duc B", "userB", "123", "userB@gmail.com"));
        list.add(new Student(3l, "Phung Duc C", "userC", "123", "userC@gmail.com"));
        list.add(new Student(4l, "Phung Duc D", "userD", "123", "userD@gmail.com"));
        list.add(new Student(5l, "Phung Duc E", "userE", "123", "userE@gmail.com"));
        list.add(new Student(6l, "Phung Duc F", "userF", "123", "userF@gmail.com"));
        list.add(new Student(7l, "Phung Duc H", "userH", "123", "userH@gmail.com"));
        list.add(new Student(8l, "Phung Duc G", "userG", "123", "userG@gmail.com"));
        list.add(new Student(9l, "Phung Duc K", "userK", "123", "userK@gmail.com"));
        list.add(new Student(10l, "Test A", "testA", "123", "testA@gmail.com"));
        list.add(new Student(11l, "Test B", "testB", "123", "testB@gmail.com"));
        list.add(new Student(12l, "Test C", "testC", "123", "testC@gmail.com"));
        list.add(new Student(13l, "Test D", "testD", "123", "testD@gmail.com"));
        return list;
    }

    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
//        String url = "http://dantri.com.vn";
//        Document document = (Document) Jsoup.connect(url).get();
//        try {
//            Date date = new Date();//ngay hien tai
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            String strDate = formatter.format(date);//tenFile
//            strDate = strDate.replaceAll("/", "");
//            FileWriter fw = new FileWriter(strDate + ".txt");
//            fw.write(document.body().text());//ghi vao file
//            fw.close();
//            System.out.println("Coppy to file successfull");
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        Long current = System.currentTimeMillis();
        List<Student> origin = generateListStudent();
        String[] listNickname = {"userA", "userB", "userC", "userE", "userF", "userG", "testA", "testD"};
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        List<Student> ketqua = new ArrayList<>();
        List<RunCallable> listWork = new ArrayList<>();
        for (int i = 0; i < listNickname.length; i++) {
            listWork.add(new RunCallable(listNickname[i], origin));
        }
        List<Future<Student>> taskFutures = executorService.invokeAll(listWork);
        taskFutures.forEach(res -> {
            try {
                ketqua.add(res.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        System.out.println(System.currentTimeMillis() - current);
        System.out.println("Ket qua: ");
        for (int i = 0; i < ketqua.size(); i++) {
            System.out.println(ketqua.get(i));
        }
    }
}

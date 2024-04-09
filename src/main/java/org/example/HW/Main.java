package org.example.HW;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        SchoolDB schoolDB = new SchoolDB();

        System.out.println(schoolDB.readAll());

        schoolDB.addData("радиотехника и электроника",4);
        schoolDB.addData("биотехнические системы и технологии", 4);
        schoolDB.addData("Самолето и вертолето строение", 4);
        schoolDB.addData("Наземные транспортно-технологические средства", 6);

        System.out.println(schoolDB.readAll());

        schoolDB.removeData(1);
        schoolDB.removeData(3);

        System.out.println(schoolDB.readAll());

        System.out.println(schoolDB.read(2));

        schoolDB.updateData(2, "Профессиональная лень", 8);

        System.out.println(schoolDB.read(2));

        schoolDB.removeAllData();
    }
}
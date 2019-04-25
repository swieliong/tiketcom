package com.tiket.demo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.EAN;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Ryan Rahardjo on 4/25/2019
 */
public class JavaBasicA {
    public static void main (String[] args){
        String userInput;
        Scanner sn = new Scanner(System.in);

        System.out.print("Masukkan jumlah mahasiswanya : ");
        int jumlahMhs = Integer.parseInt(sn.next());

        List<Mahasiswa> mahasiswas = new ArrayList<>();
        for(int i=1; i<=jumlahMhs; i++){
            Mahasiswa mahasiswa = new Mahasiswa();

            System.out.println("======> Mahasiswa "+ i);
            System.out.print("Masukkan NIM : ");
            mahasiswa.setNim(sn.next());

            System.out.print("Masukkan Nama : ");
            mahasiswa.setNama(sn.next());

            System.out.print("Masukkan Nilai Kehadiran : ");
            mahasiswa.setNilaiKehadiran(Integer.parseInt(sn.next()));

            System.out.print("Masukkan Nilai Midtest : ");
            mahasiswa.setNilaiMidtest(Integer.parseInt(sn.next()));

            System.out.print("Masukkan Nilai UAS : ");
            mahasiswa.setNilaiUas(Integer.parseInt(sn.next()));

            mahasiswa.setNilaiAkhir(.2*mahasiswa.getNilaiKehadiran() + .4*mahasiswa.getNilaiMidtest() + .4*mahasiswa.getNilaiUas());

            if (mahasiswa.getNilaiAkhir() > 84)
                mahasiswa.setGrade("A");
            else if (mahasiswa.getNilaiAkhir() > 75)
                mahasiswa.setGrade("B");
            else if (mahasiswa.getNilaiAkhir() > 60)
                mahasiswa.setGrade("C");
            else if (mahasiswa.getNilaiAkhir() > 45)
                mahasiswa.setGrade("D");
            else
                mahasiswa.setGrade("E");

            mahasiswas.add(mahasiswa);
        }

        int lulus = 0;
        int tidakLulus = 0;
        System.out.println(String.format("%-10s %-20s %-20s %-10s %-10s", "No.", "NIM", "Nama", "Nilai Akhir", "Grade"));
        System.out.println("=========================================================================");
        for (int i = 0; i<mahasiswas.size(); i++) {
            Mahasiswa item = mahasiswas.get(i);
            System.out.println(String.format("%-10x %-20s %-20s %-10f %-10s", i+1, item.getNim(), item.getNama(), item.getNilaiAkhir(), item.getGrade()));

            if ("ABC".contains(item.getGrade())) {
                lulus++;
            } else {
                tidakLulus++;
            }
        }
        System.out.println("=========================================================================");
        System.out.println("Jumlah Mahasiswa : " + mahasiswas.size());
        System.out.println("Jumlah Mahasiswa yg Lulus : " + lulus);
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + tidakLulus);
        System.out.println("=========================================================================");
    }
}

@Data
@ToString
class Mahasiswa {
    private String nim;
    private String nama;
    private double nilaiKehadiran;
    private double nilaiMidtest;
    private double nilaiUas;
    private double nilaiAkhir;
    private String grade;
}
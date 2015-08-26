package com.lemonlab.ssuapp.Model;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lk on 2015. 8. 4..
 */
public class SsuSubject {

    private Map<String, HashMap<String, Integer>> map;
    private HashMap<String, Integer> s10;
    private HashMap<String, Integer> s20;
    private HashMap<String, Integer> s30;
    private HashMap<String, Integer> s40;
    private HashMap<String, Integer> s50;
    private HashMap<String, Integer> s60;
    private HashMap<String, Integer> s70;
    private HashMap<String, Integer> s80;
    private HashMap<String, Integer> s90;
    private HashMap<String, Integer> s100;
    private HashMap<String, Integer> s110;
    private HashMap<String, Integer> s200;
    private HashMap<String, Integer> s300;
    private HashMap<String, Integer> s400;
    private HashMap<String, Integer> s500;
    private HashMap<String, Integer> s600;
    private HashMap<String, Integer> s700;

    public SsuSubject(){
        map = new HashMap<>();
        inits10();
        inits20();
        inits30();
        inits40();
        inits50();
        inits60();
        inits70();
        inits80();
        inits90();
        inits100();
        inits110();
        inits200();
        inits300();
        inits400();
        inits500();
        inits600();
        inits700();
        initMap();
    }

    public ArrayList<String> getList(String bigItem){
        HashMap<String, Integer> result = map.get(bigItem);
        return new ArrayList<String>(result.keySet());
    }

    public int getCode(String  bigItem, String smallItem){
        HashMap<String, Integer> result = map.get(bigItem);
        return result.get(smallItem);
    }

    private void initMap() {
        map.put("인문대학", s10);
        map.put("자연과학대학", s20);
        map.put("법학대학", s30);
        map.put("사회과학대학", s40);
        map.put("경제통상대학", s50);
        map.put("경영대학", s60);
        map.put("공과대학", s70);
        map.put("IT대학", s80);
        map.put("베어드학부대학", s90);
        map.put("예술창작학부", s100);
        map.put("스포츠학부", s110);
        map.put("교양필수", s200);
        map.put("교양선택", s300);
        map.put("교직", s400);
        map.put("평생교육사", s500);
        map.put("일반선택", s600);
        map.put("연계전공", s700);

    }

    private void inits10(){
        s10 = new HashMap<>();
        s10.put("기독교학과",11);
        s10.put("국어국문학과",12);
        s10.put("영어영문학과",13);
        s10.put("독어독문학과",14);
        s10.put("불어불문학과",15);
        s10.put("중어중문학과",16);
        s10.put("일어일문학과",17);
        s10.put("철학과",18);
        s10.put("사학과",19);
    }

    private void inits20() {
        s20 = new HashMap<>();
        s20.put("수학과",21);
        s20.put("물리학과",22);
        s20.put("화학과",23);
        s20.put("정보통계보험수리학과",24);
        s20.put("의생명시스템학부",25);
    }


    private void inits30() {
        s30 = new HashMap<>();
        s30.put("법학과",31);
        s30.put("국제법무학과",32);
    }


    private void inits40() {
        s40 = new HashMap<>();
        s40.put("사회복지학부",41);
        s40.put("행정학부",42);
        s40.put("정치외교학과",43);
        s40.put("정보사회학과",44);
        s40.put("언론홍보학과",45);
        s40.put("평생교육학과",46);
    }


    private void inits50() {
        s50 = new HashMap<>();
        s50.put("경제학과",51);
        s50.put("글로벌통상학과",52);
        s50.put("금융경제학과",53);
        s50.put("국제무역학과",54);
    }


    private void inits60() {
        s60 = new HashMap<>();
        s60.put("경영학부",61);
        s60.put("회계학과",62);
        s60.put("벤처중소기업학과",63);
        s60.put("금융학부",64);
        s60.put("혁신경영학과",65);
        s60.put("벤처경영학과",66);
        s60.put("스토리텔링경영학과",67);
    }

    private void inits70() {
        s70 = new HashMap<>();
        s70.put("화학공학과",71);
        s70.put("유기신소재파이버공학과",72);
        s70.put("전기공학부",73);
        s70.put("기계공학과",74);
        s70.put("산업정보시스템공학과",75);
        s70.put("건축학부",76);
        s70.put("건축공학전공",77);
        s70.put("건축학전공",78);
        s70.put("실내건축전공",79);
    }

    private void inits80() {
        s80 = new HashMap<>();
        s80.put("컴퓨터학부",81);
        s80.put("소프트웨어학부",82);
        s80.put("정보통신전자공학부",83);
        s80.put("전자공학과",84);
        s80.put("스마트시스템소프트웨어학과",85);
        s80.put("글로벌미디어학부",86);
        s80.put("전자정보공학부(전자정보전공)",87);
        s80.put("전자정보공학부(IT융합전공)",88);
        s80.put("미디어경영학과",89);
    }

    private void inits90(){
        s90 = new HashMap<>();
        s90.put("국제학전공(국제개발협력학전공)",91);
        s90.put("국제학전공(동아시아문화학전공)",92);
        s90.put("인문사회계자유전공학부",93);
        s90.put("Premed이공계자유전공학부",94);
    }

    private void inits100(){
        s100 = new HashMap<>();
        s100.put("문예창작전공",101);
        s100.put("영화예술전공",102);
    }

    private void inits110(){
        s110 = new HashMap<>();
        s110.put("스포츠학부",111);
    }

    private void inits200(){
        s200 = new HashMap<>();
        s200.put("영어1",201);
        s200.put("영어2",202);
        s200.put("현대인과성서1",203);
        s200.put("창의적사고와글쓰기",204);
        s200.put("섬김의리더십",205);
        s200.put("영어2(고급)",206);
        s200.put("한반도평화와통일",207);
        s200.put("창의적사고와독서토론",208);
        s200.put("숭실인의역량과진로탐색2",209);
    }

    private void inits300(){
        s300 = new HashMap<>();
        s300.put("문학과예술(융합-인문)",301);
        s300.put("역사와철학(융합-인문)",302);
        s300.put("정보와기술(융합-자연)",303);
        s300.put("창의성과의사소통능력(핵심-창의)",304);
        s300.put("세계의언어(핵심-창의)",305);
        s300.put("세계인의문화와국제관계(핵심-창의)",306);
        s300.put("인간과사회(융합-사회)",307);
        s300.put("정치와경제(융합-사회)",308);
        s300.put("자연과학과수리(융합-자연)",309);
        s300.put("생활과건강(실용-생활)",310);
        s300.put("학문과진로탐색(실용-생활)",311);
        s300.put("인성과리더십(핵심-창의)",312);
    }

    private void inits400(){
        s400 = new HashMap<>();
        s400.put("교직",401);
    }

    private void inits500(){
        s500 = new HashMap<>();
        s500.put("평생교육사",501);
    }

    private void inits600(){
        s600 = new HashMap<>();
        s600.put("일반선택",601);
    }

    private void inits700(){
        s700 = new HashMap<>();
        s700.put("중국어경제국제통상연계전공",701);
        s700.put("일본어경제국제통상연계전공",702);
        s700.put("금융공학-보험계리연계전공",703);
        s700.put("영어-중국어연계전공",704);
        s700.put("PreMed연계전공",705);
        s700.put("벤처자본경제학연계전공",706);
        s700.put("디지털미디어-벤처창업연계전공",707);
        s700.put("보험계리-리스크연계전공",708);
    }

}

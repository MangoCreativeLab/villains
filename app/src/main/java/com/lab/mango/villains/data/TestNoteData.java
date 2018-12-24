package com.lab.mango.villains.data;

import com.lab.mango.villains.R;

import java.util.ArrayList;
import java.util.List;

public class TestNoteData {

    public static List<Note> getData() {
        List<Note> notes = new ArrayList<>();

        notes.add(new Note("상디를 찾아라"
                , "목표는 오올 블루를 찾는것. 해상 레스토랑 발라티에 부주방장 출신으로 요리 실력은 그야말로 세계제일."
                , 0
                , 77000000, R.drawable.poster1, true));

        notes.add(new Note("몽키 D. 루피를 찾아라"
                , "밀짚모자 일당의 선장. 이명은 밀짚모자. 고무고무 열매를 먹은 전신 고무 인간"
                , 1
                , 400000000, R.drawable.poster2, true));

        notes.add(new Note("롤로노아 조로를 찾아라"
                , "세계 최강의 검사 쥬라큘 미호크를 뛰어넘는 것이 목표인 삼도류(三刀流)의 검사"
                , 2
                , 321000000, R.drawable.poster3, true));

        notes.add(new Note("버기를 찾아라"
                , "동강동강 열매 능력자로 흡시 강철 지그 처럼 자유자재로 자기 몸을 동강"
                , 3
                , 15000000, R.drawable.poster4, false));

        notes.add(new Note("나미를 찾아라"
                , "특기는 항해와 도둑질, 좋아하는 것은 귤과 돈. 목표는 세계지도를 그리는 것"
                , 4
                , 66000000, R.drawable.poster5, false));

        return notes;
    }
}

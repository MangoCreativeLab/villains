package com.lab.mango.villains.data;

import java.util.ArrayList;
import java.util.List;

public class VirtualNoteDetailForTest {

    public static final int NoteDetailCount = 5;

    public static NoteDetail getNoteDetail(int noteDetailId) {
        List<NoteDetail> notes;
        for (int i = 0; i < NoteDetailCount; i++) {
            notes = getNoteDetails(i);
            for (NoteDetail detail : notes) {
                if (detail.getId() == noteDetailId) {
                    return detail;
                }
            }
        }

        return null;
    }

    public static List<NoteDetail> getNoteDetails(int noteId) {
        List<NoteDetail> notes = new ArrayList<>();

        switch (noteId) {
            case 0:
                notes.add(new NoteDetail("문제 #1"
                        , "개신남"
                        , 0
                        , "개신남 개신남 개신남"
                        , "CYpxdSaHKEo", true));

                notes.add(new NoteDetail("문제  #2"
                        , "라스베가스 벨라지오 호텔 분수쇼"
                        , 1
                        , "라스베가스 벨라지오 호텔 분수쇼 라스베가스 벨라지오 호텔 분수쇼 라스베가스 벨라지오 호텔 분수쇼"
                        , "43_3aWy3DX8", true));

                notes.add(new NoteDetail("문제  #3"
                        , "개기욤"
                        , 2
                        , "개기욤 개기욤 개기욤"
                        , "I2ahz1hYkos", false));

                notes.add(new NoteDetail("문제  #4"
                        , "라스베가스 베레라지오 호텔 분수쇼"
                        , 3
                        , "라스베가스 베레라지오 호텔 분수쇼 라스베가스 베레라지오 호텔 분수쇼 라스베가스 베레라지오 호텔 분수쇼"
                        , "m-bas7PCpe8", false));

                notes.add(new NoteDetail("문제  #5"
                        , "하와이 포시즌스 호텔"
                        , 4
                        , "하와이 포시즌스 호텔 하와이 포시즌스 호텔 하와이 포시즌스 호텔"
                        , "fvN1q1xsL7Y", false));
                break;
            case 1:
                notes.add(new NoteDetail("루피 #1"
                        , "원피스 명장면 - 루피 vs 카타쿠리!! (18) 마지막영상"
                        , 5
                        , "루피 #1 원피스 명장면 - 루피 vs 카타쿠리!! (18) 마지막영상"
                        , "UnRt65Q8CwQ", true));

                notes.add(new NoteDetail("루피 #2"
                        , "[날뫼] 원피스 2년후 루피 vs 파시피스타"
                        , 6
                        , "루피 #2 [날뫼] 원피스 2년후 루피 vs 파시피스타"
                        , "Rby30c_iOGE", true));

                notes.add(new NoteDetail("루피 #3"
                        , "[원피스]루피와 핸콕! 첫 포옹!"
                        , 7
                        , "루피 #3 [원피스]루피와 핸콕! 첫 포옹!"
                        , "sJ-euHrj-UQ", false));

                notes.add(new NoteDetail("루피 #4"
                        , "원피스 에피소드 오브 사보 - 루피와 사보의 재회!"
                        , 8
                        , "루피 #4 원피스 에피소드 오브 사보 - 루피와 사보의 재회!"
                        , "WDIYBSzy9YQ", false));

                notes.add(new NoteDetail("루피 #5"
                        , "[날뫼]원피스 마르코보고 루피죽게 하지말라는 흰수염"
                        , 9
                        , "[날뫼]원피스 마르코보고 루피죽게 하지말라는 흰수염"
                        , "MFe2rsgvd6w", false));
                break;
            case 2:
                notes.add(new NoteDetail("조로 #1"
                        , "[원피스] 조로 vs 쇼군 오로치 조로가 곧 사용하게 될 2대귀철 귀기 스사노오!(뇌피셜록)"
                        , 10
                        , "조로 #1 [원피스] 조로 vs 쇼군 오로치 조로가 곧 사용하게 될 2대귀철 귀기 스사노오!(뇌피셜록)"
                        , "DcY7VDRDH8g", true));

                notes.add(new NoteDetail("조로 #2"
                        , "[원피스] 조로 흑도 슈스이 첫 시험"
                        , 11
                        , "조로 #2 원피스] 조로 흑도 슈스이 첫 시험"
                        , "86uZ5wwc0AU", true));

                notes.add(new NoteDetail("조로 #3"
                        , "원피스 조로 vs 후지토라 대장과 박빙인 조로 개씹대박 영상!! 루피"
                        , 12
                        , "조로 #3 원피스 조로 vs 후지토라 대장과 박빙인 조로 개씹대박 영상!! 루피"
                        , "hqy0opUXZbw", false));

                notes.add(new NoteDetail("조로 #4"
                        , "【애니 명장면】 아...아무일도 없었다 [원피스 조로]"
                        , 13
                        , "조로 #4 【애니 명장면】 아...아무일도 없었다 [원피스 조로]"
                        , "SIq4JLltG6g", false));

                notes.add(new NoteDetail("조로 #5"
                        , "[미코] 원피스 조로 명장면 모음 (조로 매드무비)"
                        , 14
                        , "조로 #5 [미코] 원피스 조로 명장면 모음 (조로 매드무비)"
                        , "ugogkhKKy6Y", false));
                break;
            case 3:
                notes.add(new NoteDetail("버기 #1"
                        , "샹크스 버기 재회"
                        , 15
                        , "버기 #1 샹크스 버기 재회"
                        , "KAow8x24qWY", true));

                notes.add(new NoteDetail("버기 #2"
                        , "[원피스] 버기 최강설 이거 개웃기다ㅋㅋㅋㅋ"
                        , 16
                        , "버기 #2 [원피스] 버기 최강설 이거 개웃기다ㅋㅋㅋㅋ"
                        , "8w4U5AH3gUA", true));

                notes.add(new NoteDetail("버기 #3"
                        , "2년 후 칠무해 광대 버기의 근황 진짜 아이디어 하나는 갑이다 해적 파견 회사라니 ㅋㅋ"
                        , 17
                        , "버기 #3 2년 후 칠무해 광대 버기의 근황 진짜 아이디어 하나는 갑이다 해적 파견 회사라니 ㅋㅋ"
                        , "07YFf8HpGqE", false));

                notes.add(new NoteDetail("버기 #4"
                        , "뜨거운 뉴스 - 원피스 버기 주가 상승 장면과 주변 사람들의 반응"
                        , 18
                        , "버기 #4 뜨거운 뉴스 - 원피스 버기 주가 상승 장면과 주변 사람들의 반응"
                        , "7q0lLW3L3sc", false));

                notes.add(new NoteDetail("버기 #5"
                        , "[원피스] 버기의 말에 감동 받은 전보벌레"
                        , 19
                        , "버기 #5 [원피스] 버기의 말에 감동 받은 전보벌레"
                        , "lsf7WCfR0OI", false));
                break;
            case 4:
                notes.add(new NoteDetail("나미 #1"
                        , "원피스 명장면 후방주의 나미 몸매 지림주의kk"
                        , 20
                        , "나미 #1 원피스 명장면 후방주의 나미 몸매 지림주의kk"
                        , "6X6x13X8BtA", true));

                notes.add(new NoteDetail("나미 #2"
                        , "[원피스] 나미한테 협박받는 제우스"
                        , 21
                        , "나미 #2 [원피스] 나미한테 협박받는 제우스"
                        , "vn1RcEBFLd4", true));

                notes.add(new NoteDetail("나미 #3"
                        , "원피스 나미에게 찝쩝대는 가짜 밀집모자 해적단"
                        , 22
                        , "나미 #3 원피스 나미에게 찝쩝대는 가짜 밀집모자 해적단"
                        , "iUm1_lezEVk", false));

                notes.add(new NoteDetail("나미 #4"
                        , "원피스 798화 나미의 썬더볼트 템포!! (브륄레와 대결)"
                        , 23
                        , "나미 #4 원피스 798화 나미의 썬더볼트 템포!! (브륄레와 대결)"
                        , "Ukj7nOVHWUI", false));

                notes.add(new NoteDetail("나미 #5"
                        , "원피스 더빙)드디어 감옥에서 탈출한 나미와 루피! +징베 도움"
                        , 24
                        , "나미 #5 원피스 더빙)드디어 감옥에서 탈출한 나미와 루피! +징베 도움"
                        , "d2PQm616tJI", false));

                break;
            default:
                notes.add(new NoteDetail("추리 #1"
                        , "목표는 오올 블루를 찾는것. 해상 레스토랑 발라티에 부주방장 출신으로 요리 실력은 그야말로 세계제일."
                        , 25
                        , "유명한 사립 탐정 '코난' 납치?", "zbfjFtU-LjA", true));

                notes.add(new NoteDetail("추리 #2"
                        , "밀짚모자 일당의 선장. 이명은 밀짚모자. 고무고무 열매를 먹은 전신 고무 인간."
                        , 26
                        , "유명한 사립 탐정 '코난' 납치?", "zbfjFtU-LjA", true));

                notes.add(new NoteDetail("추리 #3"
                        , "세계 최강의 검사 쥬라큘 미호크를 뛰어넘는 것이 목표인 삼도류(三刀流)의 검사."
                        , 27
                        , "유명한 사립 탐정 '코난' 납치?", "zbfjFtU-LjA", false));

                notes.add(new NoteDetail("추리 #4"
                        , "동강동강 열매 능력자로 흡시 강철 지그 처럼 자유자재로 자기 몸을 동강."
                        , 28
                        , "유명한 사립 탐정 '코난' 납치?", "zbfjFtU-LjA", false));

                notes.add(new NoteDetail("추리 #5"
                        , "특기는 항해와 도둑질, 좋아하는 것은 귤과 돈. 목표는 세계지도를 그리는 것."
                        , 29
                        , "유명한 사립 탐정 '코난' 납치?", "zbfjFtU-LjA", false));
                break;
        }

        return notes;
    }
}

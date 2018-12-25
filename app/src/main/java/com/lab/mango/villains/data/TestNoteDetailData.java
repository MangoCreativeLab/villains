package com.lab.mango.villains.data;

import com.lab.mango.villains.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class TestNoteDetailData {

    public static NoteDetail getNoteDetail(int noteDetailId) {
        List<NoteDetail> notes;
        for (int i = 0; i < Const.CARDVIEW_COUNT; i++) {
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

        List<String> youtubeUrl = new ArrayList<>();

        switch (noteId) {
            case 0:
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("CYpxdSaHKEo");
                notes.add(new NoteDetail(noteId, "문제 #1"
                        , "개신남"
                        , 0
                        , "개신남 개신남 개신남"
                        , youtubeUrl, true, true));

                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("43_3aWy3DX8");
                youtubeUrl.add("CYpxdSaHKEo");
                notes.add(new NoteDetail(noteId, "문제  #2"
                        , "라스베가스 벨라지오 호텔 분수쇼"
                        , 1
                        , "라스베가스 벨라지오 호텔 분수쇼 라스베가스 벨라지오 호텔 분수쇼 라스베가스 벨라지오 호텔 분수쇼"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("I2ahz1hYkos");
                youtubeUrl.add("43_3aWy3DX8");
                youtubeUrl.add("CYpxdSaHKEo");
                notes.add(new NoteDetail(noteId, "문제  #3"
                        , "개기욤"
                        , 2
                        , "개기욤 개기욤 개기욤"
                        , youtubeUrl, false, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("m-bas7PCpe8");
                notes.add(new NoteDetail(noteId, "문제  #4"
                        , "라스베가스 베레라지오 호텔 분수쇼"
                        , 3
                        , "라스베가스 베레라지오 호텔 분수쇼 라스베가스 베레라지오 호텔 분수쇼 라스베가스 베레라지오 호텔 분수쇼"
                        , youtubeUrl, false, false));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("fvN1q1xsL7Y");
                notes.add(new NoteDetail(noteId, "문제  #5"
                        , "하와이 포시즌스 호텔"
                        , 4
                        , "하와이 포시즌스 호텔 하와이 포시즌스 호텔 하와이 포시즌스 호텔"
                        , youtubeUrl, false, false));
                break;
            case 1:
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("UnRt65Q8CwQ");
                notes.add(new NoteDetail(noteId, "루피 #1"
                        , "원피스 명장면 - 루피 vs 카타쿠리!! (18) 마지막영상"
                        , 5
                        , "루피 #1 원피스 명장면 - 루피 vs 카타쿠리!! (18) 마지막영상"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("Rby30c_iOGE");
                notes.add(new NoteDetail(noteId, "루피 #2"
                        , "[날뫼] 원피스 2년후 루피 vs 파시피스타"
                        , 6
                        , "루피 #2 [날뫼] 원피스 2년후 루피 vs 파시피스타"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("sJ--UQeuHrj");
                notes.add(new NoteDetail(noteId, "루피 #3"
                        , "[원피스]루피와 핸콕! 첫 포옹!"
                        , 7
                        , "루피 #3 [원피스]루피와 핸콕! 첫 포옹!"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("WDIYBSzy9YQ");
                notes.add(new NoteDetail(noteId, "루피 #4"
                        , "원피스 에피소드 오브 사보 - 루피와 사보의 재회!"
                        , 8
                        , "루피 #4 원피스 에피소드 오브 사보 - 루피와 사보의 재회!"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("MFe2rsgvd6w");
                notes.add(new NoteDetail(noteId, "루피 #5"
                        , "[날뫼]원피스 마르코보고 루피죽게 하지말라는 흰수염"
                        , 9
                        , "[날뫼]원피스 마르코보고 루피죽게 하지말라는 흰수염"
                        , youtubeUrl, true, true));
                break;
            case 2:
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("DcY7VDRDH8g");
                notes.add(new NoteDetail(noteId, "조로 #1"
                        , "[원피스] 조로 vs 쇼군 오로치 조로가 곧 사용하게 될 2대귀철 귀기 스사노오!(뇌피셜록)"
                        , 10
                        , "조로 #1 [원피스] 조로 vs 쇼군 오로치 조로가 곧 사용하게 될 2대귀철 귀기 스사노오!(뇌피셜록)"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("86uZ5wwc0AU");
                notes.add(new NoteDetail(noteId, "조로 #2"
                        , "[원피스] 조로 흑도 슈스이 첫 시험"
                        , 11
                        , "조로 #2 원피스] 조로 흑도 슈스이 첫 시험"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("hqy0opUXZbw");
                notes.add(new NoteDetail(noteId, "조로 #3"
                        , "원피스 조로 vs 후지토라 대장과 박빙인 조로 개씹대박 영상!! 루피"
                        , 12
                        , "조로 #3 원피스 조로 vs 후지토라 대장과 박빙인 조로 개씹대박 영상!! 루피"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("SIq4JLltG6g");
                notes.add(new NoteDetail(noteId, "조로 #4"
                        , "【애니 명장면】 아...아무일도 없었다 [원피스 조로]"
                        , 13
                        , "조로 #4 【애니 명장면】 아...아무일도 없었다 [원피스 조로]"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("ugogkhKKy6Y");
                notes.add(new NoteDetail(noteId, "조로 #5"
                        , "[미코] 원피스 조로 명장면 모음 (조로 매드무비)"
                        , 14
                        , "조로 #5 [미코] 원피스 조로 명장면 모음 (조로 매드무비)"
                        , youtubeUrl, true, true));
                break;
            case 3:
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("KAow8x24qWY");
                notes.add(new NoteDetail(noteId, "버기 #1"
                        , "샹크스 버기 재회"
                        , 15
                        , "버기 #1 샹크스 버기 재회"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("8w4U5AH3gUA");
                notes.add(new NoteDetail(noteId, "버기 #2"
                        , "[원피스] 버기 최강설 이거 개웃기다ㅋㅋㅋㅋ"
                        , 16
                        , "버기 #2 [원피스] 버기 최강설 이거 개웃기다ㅋㅋㅋㅋ"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("07YFf8HpGqE");
                notes.add(new NoteDetail(noteId, "버기 #3"
                        , "2년 후 칠무해 광대 버기의 근황 진짜 아이디어 하나는 갑이다 해적 파견 회사라니 ㅋㅋ"
                        , 17
                        , "버기 #3 2년 후 칠무해 광대 버기의 근황 진짜 아이디어 하나는 갑이다 해적 파견 회사라니 ㅋㅋ"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("7q0lLW3L3sc");
                notes.add(new NoteDetail(noteId, "버기 #4"
                        , "뜨거운 뉴스 - 원피스 버기 주가 상승 장면과 주변 사람들의 반응"
                        , 18
                        , "버기 #4 뜨거운 뉴스 - 원피스 버기 주가 상승 장면과 주변 사람들의 반응"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("lsf7WCfR0OI");
                notes.add(new NoteDetail(noteId, "버기 #5"
                        , "[원피스] 버기의 말에 감동 받은 전보벌레"
                        , 19
                        , "버기 #5 [원피스] 버기의 말에 감동 받은 전보벌레"
                        , youtubeUrl, true, true));
                break;
            case 4:
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("6X6x13X8BtA");
                notes.add(new NoteDetail(noteId, "나미 #1"
                        , "원피스 명장면 후방주의 나미 몸매 지림주의kk"
                        , 20
                        , "나미 #1 원피스 명장면 후방주의 나미 몸매 지림주의kk"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("vn1RcEBFLd4");
                notes.add(new NoteDetail(noteId, "나미 #2"
                        , "[원피스] 나미한테 협박받는 제우스"
                        , 21
                        , "나미 #2 [원피스] 나미한테 협박받는 제우스"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("iUm1_lezEVk");
                notes.add(new NoteDetail(noteId, "나미 #3"
                        , "원피스 나미에게 찝쩝대는 가짜 밀집모자 해적단"
                        , 22
                        , "나미 #3 원피스 나미에게 찝쩝대는 가짜 밀집모자 해적단"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("Ukj7nOVHWUI");
                notes.add(new NoteDetail(noteId, "나미 #4"
                        , "원피스 798화 나미의 썬더볼트 템포!! (브륄레와 대결)"
                        , 23
                        , "나미 #4 원피스 798화 나미의 썬더볼트 템포!! (브륄레와 대결)"
                        , youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("d2PQm616tJI");
                notes.add(new NoteDetail(noteId, "나미 #5"
                        , "원피스 더빙)드디어 감옥에서 탈출한 나미와 루피! +징베 도움"
                        , 24
                        , "나미 #5 원피스 더빙)드디어 감옥에서 탈출한 나미와 루피! +징베 도움"
                        , youtubeUrl, true, true));

                break;
            default:
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("zbfjFtU-LjA");
                notes.add(new NoteDetail(noteId, "추리 #1"
                        , "목표는 오올 블루를 찾는것. 해상 레스토랑 발라티에 부주방장 출신으로 요리 실력은 그야말로 세계제일."
                        , 25
                        , "유명한 사립 탐정 '코난' 납치?", youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("zbfjFtU-LjA");
                notes.add(new NoteDetail(noteId, "추리 #2"
                        , "밀짚모자 일당의 선장. 이명은 밀짚모자. 고무고무 열매를 먹은 전신 고무 인간."
                        , 26
                        , "유명한 사립 탐정 '코난' 납치?", youtubeUrl, true, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("zbfjFtU-LjA");
                notes.add(new NoteDetail(noteId, "추리 #3"
                        , "세계 최강의 검사 쥬라큘 미호크를 뛰어넘는 것이 목표인 삼도류(三刀流)의 검사."
                        , 27
                        , "유명한 사립 탐정 '코난' 납치?", youtubeUrl, false, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("zbfjFtU-LjA");
                notes.add(new NoteDetail(noteId, "추리 #4"
                        , "동강동강 열매 능력자로 흡시 강철 지그 처럼 자유자재로 자기 몸을 동강."
                        , 28
                        , "유명한 사립 탐정 '코난' 납치?", youtubeUrl, false, true));
                youtubeUrl = new ArrayList<>();
                youtubeUrl.add("zbfjFtU-LjA");
                notes.add(new NoteDetail(noteId, "추리 #5"
                        , "특기는 항해와 도둑질, 좋아하는 것은 귤과 돈. 목표는 세계지도를 그리는 것."
                        , 29
                        , "유명한 사립 탐정 '코난' 납치?", youtubeUrl, false, true));
                break;
        }

        return notes;
    }
}

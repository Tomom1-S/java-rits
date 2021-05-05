package models;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StatusClientTest {
    private MockWebServer mockWebServer;

    @BeforeEach
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void fetchAttractionStatusSucceeds() {
        final var dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(final RecordedRequest request) {
                try {
                    final String mockBody = IOUtils.toString(getClass().getClassLoader()
                            .getResourceAsStream("mockResponse/tdlAttractionStatus.html"), StandardCharsets.UTF_8);
                    return new MockResponse().setResponseCode(200).setBody(mockBody);
                } catch (final IOException e) {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        final StatusClient statusClient = new StatusClient(
                "http://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort());
        final List<Facility> result = statusClient.fetchStatusList(null, FacilityType.ATTRACTION_STANDBY);
        final List<Facility> expected = Arrays.asList(
                new AttractionFacility("オムニバス", "0分"),
                new AttractionFacility("ペニーアーケード", "情報なし"),
                new AttractionFacility("メインエントランス(ベビーカー&車イス・レンタル側)", "案内終了"),
                new AttractionFacility("メインエントランス(メインストリート・ハウス側)", "案内終了"),
                new AttractionFacility("アリスのティーパーティー", "5分"),
                new AttractionFacility("イッツ・ア・スモールワールド", "5分"),
                new AttractionFacility("キャッスルカルーセル", "5分"),
                new AttractionFacility("白雪姫と七人のこびと", "10分"),
                new AttractionFacility("シンデレラのフェアリーテイル･ホール", "10分"),
                new AttractionFacility("空飛ぶダンボ", "30分"),
                new AttractionFacility("ピノキオの冒険旅行", "5分"),
                new AttractionFacility("ピーターパン空の旅", "25分"),
                new AttractionFacility("プーさんのハニーハント", "14分 （FP：16:20〜16:50）"),
                new AttractionFacility("ホーンテッドマンション", "13分"),
                new AttractionFacility("ミッキーのフィルハーマジック", "10分"),
                new AttractionFacility("スターツアーズ", "15分"),
                new AttractionFacility("スペース・マウンテン", "30分 （FP：13:00〜13:30）"),
                new AttractionFacility("バズ・ライトイヤー", "5分 （FP：13:25〜13:55）"),
                new AttractionFacility("モンスターズ・インク", "40分 （FP：12:55〜13:25）"),
                new AttractionFacility("スティッチ・エンカウンター", "15分"),
                new AttractionFacility("ガジェットのゴーコースター", "10分"),
                new AttractionFacility("グーフィーのペイント＆プレイハウス", "10分"),
                new AttractionFacility("チップとデールのツリーハウス", "0分"),
                new AttractionFacility("トゥーンパーク", "情報なし"),
                new AttractionFacility("ドナルドのボート", "0分"),
                new AttractionFacility("ミッキーの家とミート・ミッキー", "エントリー受付中です 9:00-20:00:エントリー受付中です"),
                new AttractionFacility("ミニーの家", "10分"),
                new AttractionFacility("ロジャーラビットのカートゥーンスピン", "10分"),
                new AttractionFacility("スプラッシュ・マウンテン", "20分 （FP：発券終了)"),
                new AttractionFacility("カヌー探検", "5分"),
                new AttractionFacility("シューティングギャラリー", "20分"),
                new AttractionFacility("カントリーベア・シアター", "5分"),
                new AttractionFacility("蒸気船マークトウェイン号", "5分"),
                new AttractionFacility("トムソーヤ島いかだ", "0分"),
                new AttractionFacility("ビッグサンダー・マウンテン", "25分 （FP：14:40〜15:10）"),
                new AttractionFacility("グリーティングトレイル（デイジー）", "案内終了"),
                new AttractionFacility("グリーティングトレイル（ドナルド）", "エントリー受付中です"),
                new AttractionFacility("ウエスタンリバー鉄道", "15分"),
                new AttractionFacility("カリブの海賊", "5分"),
                new AttractionFacility("スイスファミリー・ツリーハウス", "0分"),
                new AttractionFacility("魅惑のチキルーム", "5分"),
                new AttractionFacility("ジャングルクルーズ：ワイルドライフ・エクスペディション", "20分"),
                new AttractionFacility("プラザパビリオン・バンドスタンド前", "案内終了"),
                new AttractionFacility("ウッドチャック・グリーティングトレイル", "案内終了 10:00-19:00:エントリー受付中です"),
                new AttractionFacility("シアターオーリンズ（キャラクターグリーティング）", "案内終了 11:35-17:45:エントリー受付中です"),
                new AttractionFacility("ミニーのスタイルスタジオ", "案内終了 9:00-20:00:エントリー受付中です"),
                new AttractionFacility("プラザパビリオン・バンドスタンド前（キャラクターグリーティング）", "案内終了 10:15-16:25:エントリー受付中です")
        );
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void fetchFastpassStatusSucceeds() {
        final var dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(final RecordedRequest request) {
                try {
                    final String mockBody = IOUtils.toString(getClass().getClassLoader()
                            .getResourceAsStream("mockResponse/tdlFastpassStatus.html"), StandardCharsets.UTF_8);
                    return new MockResponse().setResponseCode(200).setBody(mockBody);
                } catch (final IOException e) {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        final StatusClient statusClient = new StatusClient(
                "http://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort());
        final List<Facility> result = statusClient.fetchStatusList(null, FacilityType.ATTRACTION_FASTPASS);
        final List<Facility> expected = Arrays.asList(
                new AttractionFacility("スプラッシュ・マウンテン", "発券中（14:45〜15:15） （待ち時間：15分）"),
                new AttractionFacility("ビッグサンダー・マウンテン", "発券中（13:00〜13:30） （待ち時間：20分）"),
                new AttractionFacility("プーさんのハニーハント", "発券中（13:20〜13:50） （待ち時間：30分）"),
                new AttractionFacility("モンスターズ・インク", "発券中（14:00〜14:30） （待ち時間：30分）"),
                new AttractionFacility("バズ・ライトイヤー", "発券中（14:35〜15:05） （待ち時間：20分）"),
                new AttractionFacility("スペース・マウンテン", "発券中（11:50〜12:20） （待ち時間：20分）")
        );
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void fetchGreetingStatusSucceeds() {
        final var dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(final RecordedRequest request) {
                try {
                    final String mockBody = IOUtils.toString(getClass().getClassLoader()
                            .getResourceAsStream("mockResponse/tdlGreetingStatus.html"), StandardCharsets.UTF_8);
                    return new MockResponse().setResponseCode(200).setBody(mockBody);
                } catch (final IOException e) {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        final StatusClient statusClient = new StatusClient(
                "http://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort());
        final List<Facility> result = statusClient.fetchStatusList(null, FacilityType.GREETING);
        final List<Facility> expected = Arrays.asList(
                new GreetingFacility("ミッキーの家とミート・ミッキー", "エントリー受付中です 9:00-20:00:エントリー受付中です"),
                new GreetingFacility("グリーティングトレイル（ドナルド）", "エントリー受付中です"),
                new GreetingFacility("グリーティングトレイル（デイジー）", "案内終了"),
                new GreetingFacility("メインエントランス(メインストリート・ハウス側)", "案内終了"),
                new GreetingFacility("メインエントランス(ベビーカー&車イス・レンタル側)", "案内終了")
        );
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void fetchRestaurantStatusSucceeds() {
        final var dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(final RecordedRequest request) {
                try {
                    final String mockBody = IOUtils.toString(getClass().getClassLoader()
                            .getResourceAsStream("mockResponse/tdlRestaurantStatus.html"), StandardCharsets.UTF_8);
                    return new MockResponse().setResponseCode(200).setBody(mockBody);
                } catch (final IOException e) {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        final StatusClient statusClient = new StatusClient(
                "http://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort());
        final List<Facility> result = statusClient.fetchStatusList(null, FacilityType.RESTAURANT);
        final List<Facility> expected = Arrays.asList(
                new RestaurantFacility("キャンティーン (キャラメル味)", "0分 11:00-15:00:案内終了"),
                new RestaurantFacility("ザ・ダイヤモンドホースシュー", "− 15:00-19:00 : 準備中"),
                new RestaurantFacility("チャックワゴン (ミルクチョコレート味)", "0分 08:30-21:00:案内終了"),
                new RestaurantFacility("ハングリーベア・レストラン (カレー味)", "− 10:30-19:00 : 準備中"),
                new RestaurantFacility("プラザパビリオン・レストラン (カレー味)", "− 11:00-15:00 : 準備中"),
                new RestaurantFacility("ペコスビル・カフェ (ハニー味)", "0分 〜 20分 9:30-19:00 : 運営中"),
                new RestaurantFacility("ラッキーナゲット・カフェ", "− (9:00 - 19:30)"),
                new RestaurantFacility("アイスクリームコーン", "− 11:00-18:00 : 準備中"),
                new RestaurantFacility("イーストサイド・カフェ", "− 10:30-19:00 : 運営状況は施設でご確認ください。"),
                new RestaurantFacility("グレートアメリカン・ワッフルカンパニー (キャラメル味)", "運営・公演中止"),
                new RestaurantFacility("スウィートハート・カフェ (キャラメル味)", "− 9:30-19:30 : 運営中"),
                new RestaurantFacility("センターストリート・コーヒーハウス (ミルクチョコレート味)", "− 10:30-19:00 : 準備中"),
                new RestaurantFacility("リフレッシュメントコーナー (キャラメル味)", "0分 〜 20分 9:00-19:30 : 運営中"),
                new RestaurantFacility("れすとらん北齋", "− 10:30-19:00 : 準備中"),
                new RestaurantFacility("アウト・オブ・バウンズ・アイスクリーム", "−"),
                new RestaurantFacility("ディンギードリンク (キャラメル味)", "運営状況は施設でご確認ください。 11:00-15:00"),
                new RestaurantFacility("トゥーントーン・トリート (キャラメル味)", "運営・公演中止"),
                new RestaurantFacility("トゥーンポップ (キャラメル味)", "運営・公演中止"),
                new RestaurantFacility("ヒューイ・デューイ・ルーイのグッドタイム・カフェ (カレー味)", "− 10:30-15:00 : 準備中"),
                new RestaurantFacility("ポップ・ア・ロット・ポップコーン (ブラックペッパー味)", "運営状況は施設でご確認ください。 10:00-16:00"),
                new RestaurantFacility("ミッキーのトレーラー (キャラメル味)", "運営・公演中止"),
                new RestaurantFacility("スペースプレース・フードポート", "−"),
                new RestaurantFacility("ソフトランディング (ミルクチョコレート味)", "運営・公演中止"),
                new RestaurantFacility("トゥモローランド・テラス (キャラメル味)", "0分 〜 20分 9:30-19:00 : 運営中"),
                new RestaurantFacility("パン・ギャラクティック・ピザ・ポート (カレー味)", "0分 〜 20分 10:00-19:00 : 運営中"),
                new RestaurantFacility("プラザ・レストラン", "−"),
                new RestaurantFacility("ポッピングポッド (ミルクチョコレート味)", "運営状況は施設でご確認ください。 10:00-15:00"),
                new RestaurantFacility("ライトバイト・サテライト", "−"),
                new RestaurantFacility("キャプテンフックス・ギャレー (キャラメル味)", "0分 〜 20分 10:00-17:00 : 運営中"),
                new RestaurantFacility("クイーン・オブ・ハートのバンケットホール (キャラメル味)", "0分 〜 20分 10:00-19:00 : 運営中"),
                new RestaurantFacility("クレオズ (キャラメル味)", "運営・公演中止"),
                new RestaurantFacility("トルバドールタバン (キャラメル味)", "運営・公演中止"),
                new RestaurantFacility("ビレッジペイストリー (カレー味)", "0分 〜 20分 10:00-19:00 : 運営中"),
                new RestaurantFacility("グランマ・サラのキッチン (キャラメル味)", "− 11:00-19:00 : 準備中"),
                new RestaurantFacility("ラケッティのラクーンサルーン (キャラメル味)", "運営・公演中止"),
                new RestaurantFacility("カフェ・オーリンズ", "− 10:30-17:30 : 準備中"),
                new RestaurantFacility("クリスタルパレス・レストラン (キャラメル味)", "− 11:00-17:00 : 準備中"),
                new RestaurantFacility("ザ・ガゼーボ (キャラメル味)", "− 13:00-16:00 : 準備中"),
                new RestaurantFacility("スキッパーズ・ギャレー (ミルクチョコレート味)", "0分 〜 20分 10:00-17:00 : 運営中"),
                new RestaurantFacility("スクウィーザーズ・トロピカル・ジュースバー (ミルクチョコレート味)", "運営・公演中止"),
                new RestaurantFacility("チャイナボイジャー (ミルクチョコレート味)", "0分 〜 20分 10:00-19:00 : 運営中"),
                new RestaurantFacility("パークサイドワゴン (カレー味)", "運営・公演中止"),
                new RestaurantFacility("フレッシュフルーツオアシス (カレー味)", "運営・公演中止"),
                new RestaurantFacility("ブルーバイユー・レストラン (カレー味)", "− 10:30-18:30 : 準備中"),
                new RestaurantFacility("ボイラールーム・バイツ (ハニー味)", "運営・公演中止"),
                new RestaurantFacility("ポリネシアンテラス・レストラン", "− 11:00-15:00 : 準備中"),
                new RestaurantFacility("ロイヤルストリート・ベランダ (キャラメル味)", "− 12:00-15:00 : 準備中"),
                new RestaurantFacility("キャリッジハウス・リフレッシュメント (キャラメル味)", "運営・公演中止"),
                new RestaurantFacility("NEW キャンプ・ウッドチャック・キッチン", "− (9:00 - 19:30)"),
                new RestaurantFacility("NEW プラズマ・レイズ・ダイナー", "− (8:30 - 21:30)"),
                new RestaurantFacility("イベントブース　※シンデレラ城前プラザ(アドベンチャーランド側)", "0分"),
                new RestaurantFacility("イベントブース　※シンデレラ城前プラザ(トゥモローランド側)", "−"),
                new RestaurantFacility("イベントブース　※シンデレラ城前プラザ(ワールドバザール側)", "0分"),
                new RestaurantFacility("スウィートハート・カフェ前（ポップコーンワゴン） (しょうゆバター味)", "− 10:00-19:00"),
                new RestaurantFacility("カフェ・オーリンズ前（ポップコーンワゴン） (しょうゆバター味)", "−"),
                new RestaurantFacility("ザ・ガゼーボ横（ポップコーンワゴン） (キャラメル味)", "−"),
                new RestaurantFacility("ポリネシアンテラス・レストラン前（ポップコーンワゴン） (キャラメル味)", "−"),
                new RestaurantFacility("カントリーベア・シアター前（ドリンクワゴン）", "− 9:30-17:00"),
                new RestaurantFacility("ビッグサンダー・マウンテン前（ドリンクワゴン）", "− 9:30-16:00"),
                new RestaurantFacility("チャックワゴン横（ポップコーンワゴン） (キャラメル味)", "0分 08:30-21:30"),
                new RestaurantFacility("トレーディングポスト横（ポップコーンワゴン） (カレー味)", "−"),
                new RestaurantFacility("蒸気船マークトウェイン号乗り場前（ポップコーンワゴン） (キャラメル味)", "−"),
                new RestaurantFacility("キャッスルカルーセル横（ポップコーンワゴン） (ミルクチョコレート味)", "−"),
                new RestaurantFacility("プーさんのハニーハント前（ポップコーンワゴン） (ハニー味)", "−"),
                new RestaurantFacility("トゥモローランド・テラス前（ドリンクワゴン）", "−"),
                new RestaurantFacility("トレジャーコメット横（ポップコーンワゴン） (ソルト味)", "− 10:00-18:45"),
                new RestaurantFacility("NEW プラザテラスC", "−"),
                new RestaurantFacility("キャンプ・ウッドチャック・キッチン (キャラメル味)", "− 11:00-15:00 : 準備中"),
                new RestaurantFacility("プラズマ・レイズ・ダイナー (カレー味)", "− 10:30-19:00 : 準備中"),
                new RestaurantFacility("イベントブース　※シンデレラ城前プラザ（ワールドバザール側）", "−"),
                new RestaurantFacility("イベントブース　※シンデレラ城前プラザ（アドベンチャーランド側）", "−"),
                new RestaurantFacility("フードブース　※シンデレラ城前プラザ（ワールドバザール側）", "0分"),
                new RestaurantFacility("フードブース　※シンデレラ城前プラザ（アドベンチャーランド側）", "0分"),
                new RestaurantFacility("フードブース２", "− 10:00-17:00 : 案内終了"),
                new RestaurantFacility("フードブース（トゥモローランド側）", "運営・公演中止"),
                new RestaurantFacility("フードブース１", "− 10:00-17:00 : 案内終了"),
                new RestaurantFacility("マジカルマーケット", "運営・公演中止"),
                new RestaurantFacility("フードブース（アドベンチャーランド側）", "運営・公演中止")
        );
        assertThat(result).hasSameElementsAs(expected);
    }
}

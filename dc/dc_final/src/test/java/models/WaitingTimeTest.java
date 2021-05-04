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

class WaitingTimeTest {
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
    public void fetchAttractionWaitingTimeSucceeds() {
        final var dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(final RecordedRequest request) {
                try {
                    final String mockBody = IOUtils.toString(getClass().getClassLoader()
                            .getResourceAsStream("mockResponse/tdlWaitingTime.html"), StandardCharsets.UTF_8);
                    return new MockResponse().setResponseCode(200).setBody(mockBody);
                } catch (final IOException e) {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
        mockWebServer.setDispatcher(dispatcher);

        final WaitingTime waitingTime = new WaitingTime(
                "http://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort());
        final List<Facility> result = waitingTime.fetchWaitingTimes(null, FacilityType.ATTRACTION_STANDBY);
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
}

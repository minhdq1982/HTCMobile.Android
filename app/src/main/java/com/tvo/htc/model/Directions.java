package com.tvo.htc.model;

import java.util.List;

/**
 * Created by User on 12/20/2016.
 */

public class Directions {

    /**
     * geocoded_waypoints : [{"geocoder_status":"OK","place_id":"ChIJRWo23KKsNTERJl8RDXTGVrc","types":["premise"]},{"geocoder_status":"OK","place_id":"ChIJpxf_1iqpNTERlHCL_99o3gs","types":["route"]}]
     * routes : [{"bounds":{"northeast":{"lat":21.006386,"lng":105.9196732},"southwest":{"lat":20.9638558,"lng":105.8020387}},"copyrights":"Map data ©2018 Google","legs":[{"distance":{"text":"18.8 km","value":18766},"duration":{"text":"30 mins","value":1801},"end_address":"Unnamed Road, Đông Dư, Gia Lâm, Hà Nội, Vietnam","end_location":{"lat":21.0034255,"lng":105.9190374},"start_address":"15 Lê Văn Thiêm, Nhân Chính, Thanh Xuân, Hồ Chí Minh, Vietnam","start_location":{"lat":21.0016756,"lng":105.8026976},"steps":[{"distance":{"text":"31 m","value":31},"duration":{"text":"1 min","value":10},"end_location":{"lat":21.001516,"lng":105.8024492},"html_instructions":"Head <b>southwest<\/b><div style=\"font-size:0.9em\">Restricted usage road<\/div>","polyline":{"points":"o{d_C{qwdS^p@"},"start_location":{"lat":21.0016756,"lng":105.8026976},"travel_mode":"DRIVING"},{"distance":{"text":"0.1 km","value":113},"duration":{"text":"1 min","value":60},"end_location":{"lat":21.0007047,"lng":105.8031051},"html_instructions":"Turn <b>left<\/b> at Tạp Chí Nhà Thầu Và Thị Trường Xây Dựng toward <b>Ngụy Như Kon Tum<\/b><div style=\"font-size:0.9em\">Restricted usage road<\/div><div style=\"font-size:0.9em\">Pass by A&amp;S Law (on the left)<\/div>","maneuver":"turn-left","polyline":{"points":"ozd_CipwdSxAiAhAy@"},"start_location":{"lat":21.001516,"lng":105.8024492},"travel_mode":"DRIVING"},{"distance":{"text":"0.1 km","value":139},"duration":{"text":"1 min","value":46},"end_location":{"lat":20.9999496,"lng":105.8020387},"html_instructions":"Turn <b>right<\/b> at Shop Thời Trang Na Na onto <b>Ngụy Như Kon Tum<\/b><div style=\"font-size:0.9em\">Pass by Bánh Mì Mai Phương (on the right)<\/div>","maneuver":"turn-right","polyline":{"points":"kud_CmtwdSZd@v@tA`AxA"},"start_location":{"lat":21.0007047,"lng":105.8031051},"travel_mode":"DRIVING"},{"distance":{"text":"0.8 km","value":812},"duration":{"text":"3 mins","value":186},"end_location":{"lat":20.9941116,"lng":105.8067413},"html_instructions":"Turn <b>left<\/b> at VPĐD Cty Cổ Phần CN Tàu Thuỷ Hoàng Anh Tại HN onto <b>Đ. Nguyễn Tuân<\/b><div style=\"font-size:0.9em\">Pass by Cửa Hàng Sim Thẻ - Đtdđ (on the left in 400&nbsp;m)<\/div>","maneuver":"turn-left","polyline":{"points":"upd_CwmwdS|@o@fAw@t@k@NKn@e@`@YdBqAxBaBJIzCeC~@s@nA_AXSbBoA|AkAz@k@TQVSJIFG"},"start_location":{"lat":20.9999496,"lng":105.8020387},"travel_mode":"DRIVING"},{"distance":{"text":"0.4 km","value":439},"duration":{"text":"2 mins","value":104},"end_location":{"lat":20.9918111,"lng":105.8033207},"html_instructions":"Turn <b>right<\/b> after Dịch Vụ Photocopy - In 378 (on the right)<div style=\"font-size:0.9em\">Pass by Cafe &amp; Thức Ăn Nhanh Hảo Hảo (on the right)<\/div>","maneuver":"turn-right","polyline":{"points":"elc_CckxdSx@nA?F?FBHDFHPP^zFdJNVNVR\\z@rA"},"start_location":{"lat":20.9941116,"lng":105.8067413},"travel_mode":"DRIVING"},{"distance":{"text":"0.3 km","value":347},"duration":{"text":"1 min","value":83},"end_location":{"lat":20.9893519,"lng":105.8045532},"html_instructions":"Turn <b>left<\/b> onto <b>Đường Nguyễn Xiển<\/b><div style=\"font-size:0.9em\">Pass by Công Ty Cổ Phần Đầu Tư Xây Dựng 3d Panel Việt Nam (on the right)<\/div>","maneuver":"turn-left","polyline":{"points":"y}b_CwuwdSz@nAz@o@dBoAZWnBwAdB_AZU"},"start_location":{"lat":20.9918111,"lng":105.8033207},"travel_mode":"DRIVING"},{"distance":{"text":"0.3 km","value":299},"duration":{"text":"1 min","value":36},"end_location":{"lat":20.9872822,"lng":105.8062993},"html_instructions":"Slight <b>left<\/b> at Quán Vịt Phong Béo onto the <b>Đường Cao Tốc<\/b> ramp","polyline":{"points":"mnb_Cm}wdS?IBIHKLKdEkCdBqAt@k@LGJKBC?E@C?E"},"start_location":{"lat":20.9893519,"lng":105.8045532},"travel_mode":"DRIVING"},{"distance":{"text":"10.6 km","value":10637},"duration":{"text":"11 mins","value":678},"end_location":{"lat":20.9817276,"lng":105.8912596},"html_instructions":"Merge onto <b>ĐCT20<\/b>","maneuver":"merge","polyline":{"points":"oab_CkhxdSxB{ApAy@|@k@dAu@tA}@l@c@zI_Gz@i@XSpD_Cp@c@hBsAtJuGlEuCrFuDlDaCdAq@nA}@v@o@x@u@hAkAdAoAxAsBv@qA`AsBv@sBl@kBf@sB`@kBjBqJt@uDLq@x@iDjBsI^oAf@{ApEuKRi@JUz@sBFM^{@R_@n@uAd@aANg@Lc@\\}@\\y@\\u@Xs@Vw@HUX}@Nk@BMDOTkANy@VqAJw@Ho@JuAD{@DkAFiAHgBFy@Fy@D{@FgAB]B]DeAFeA@aA@Y@UBo@BYBY@W@Q?S?]?a@@a@AY@{@?gA?gA?aC?}AA}A@oA?cA?o@AiA@w@AsA?c@A{@?w@?eA?S?y@CkB?_A?gA?y@?[AqA@{@?a@BaADiAFqAJ_AJeARuCb@iF|AeP\\qDd@sE^wDd@cFLeBLmCDqA@kA?_BCkBAkAAoAAeBYaRCgBCiACkA?[GgCIkCEiAGeAQoBQuAQiAUiAScAW}@[gAe@uAQ_@O_@_AoBkAqBmAcB_BkB{AwAiB{AcBoAe@[ECyByAyCkBaBcAeAq@yBwA_BeAgAy@a@]c@_@_A{@{@cAs@}@m@{@o@cAm@iAOUISKQsA}CgB}C{@qAoA_BoEkEc@[aJyHYWiB{AmAcAw@i@cA{@g@c@y@q@}@w@g@s@"},"start_location":{"lat":20.9872822,"lng":105.8062993},"travel_mode":"DRIVING"},{"distance":{"text":"3.5 km","value":3528},"duration":{"text":"4 mins","value":254},"end_location":{"lat":21.0061194,"lng":105.9129801},"html_instructions":"Continue onto <b>QL1A<\/b>","polyline":{"points":"y~`_Ck{heSyE}Di@c@{BaBqD}CgA}@iA_AwAmAqCaCkGmFq@i@aBuAyCkCeA}@eGgF{LeLoB_ByAqA{JiIaJ}HUSUSkC}BiFoE}JuI{AqA[UYUaGgF][[Ym@i@eA}@kFmEg@i@"},"start_location":{"lat":20.9817276,"lng":105.8912596},"travel_mode":"DRIVING"},{"distance":{"text":"0.5 km","value":528},"duration":{"text":"1 min","value":74},"end_location":{"lat":21.0041071,"lng":105.9114712},"html_instructions":"Take the exit toward <b>Đê Long Biên - Xuân Quan<\/b>/<b>Bát Tràng<\/b>/<b>KĐT Ecopark TP Hưng Yên<\/b>","maneuver":"ramp-right","polyline":{"points":"gwe_CccmeS?G?GCKGMOSKSEMEOCS@QBKBMFKDIJKJEFEHARAL?J@LBHBHHLPJRDVDT?VAd@Ah@@ZF^JZLXNTRRHFb@\\JFrCtB"},"start_location":{"lat":21.0061194,"lng":105.9129801},"travel_mode":"DRIVING"},{"distance":{"text":"1.0 km","value":1033},"duration":{"text":"2 mins","value":125},"end_location":{"lat":20.9983214,"lng":105.918607},"html_instructions":"Turn <b>left<\/b> onto <b>ĐT379<\/b><div style=\"font-size:0.9em\">Pass by Mạnh Lốp (on the right)<\/div>","maneuver":"turn-left","polyline":{"points":"uje_CuyleS^VHURg@|A_EfBiEvBkFL]Re@n@aBd@aALU^k@R]LSV[\\c@x@}@l@g@jA{@|@k@bAi@bAc@hAa@fA]"},"start_location":{"lat":21.0041071,"lng":105.9114712},"travel_mode":"DRIVING"},{"distance":{"text":"0.1 km","value":117},"duration":{"text":"1 min","value":22},"end_location":{"lat":20.9986647,"lng":105.9196732},"html_instructions":"Turn <b>left<\/b>","maneuver":"turn-left","polyline":{"points":"ofd_CifneSKa@w@qD"},"start_location":{"lat":20.9983214,"lng":105.918607},"travel_mode":"DRIVING"},{"distance":{"text":"0.5 km","value":535},"duration":{"text":"2 mins","value":90},"end_location":{"lat":21.0028586,"lng":105.9171546},"html_instructions":"Turn <b>left<\/b>","maneuver":"turn-left","polyline":{"points":"shd_C}lneSC@cAh@{BfAkB`AeBz@kB~@{Av@gAh@qAp@aAh@MF"},"start_location":{"lat":20.9986647,"lng":105.9196732},"travel_mode":"DRIVING"},{"distance":{"text":"0.2 km","value":208},"duration":{"text":"1 min","value":33},"end_location":{"lat":21.0034255,"lng":105.9190374},"html_instructions":"Turn <b>right<\/b>","maneuver":"turn-right","polyline":{"points":"{be_Ce}meSCK[uAYiAS_AS}@Qy@AE?E@E@C"},"start_location":{"lat":21.0028586,"lng":105.9171546},"travel_mode":"DRIVING"}],"traffic_speed_entry":[],"via_waypoint":[]}],"overview_polyline":{"points":"o{d_C{qwdS^p@xAiAhAy@Zd@xBnDdCgBvCwB~EsDfDoClGwEfE}CRQx@nA?FBPNXlGdKNVb@t@vBbD`D_CjCoB`CuABSVWdEkCdBqAbAs@NO@I?ExB{AnCeBzCsB~MaJbFcDhBsAtJuG`MkIrFsDfCmBbCaCdAoAxAsBv@qA`AsBv@sBl@kBhA_F`DgPfA{EjBsI^oAf@{ApEuK^_AbAaChCsF\\kAz@wBv@iB`@mAr@gCd@eCb@iCTeCJgC`@eHT_EPgFLyBB{A@kL?oIAeGA}ECyFAoF@}AHkCFqAJ_A^{E`CoWbAeKdA{KZsFF}CCkE_@cZKyGQsGMoCc@eEg@sCk@aCaA}Ca@_A_AoBkAqBmAcB_BkB{AwAiB{AiCkB_C}A{LyHgD_CeA}@_A{@oBaC}A_C}@_BUe@sA}CgB}CkCqDoEkEc@[{JqIwD_D{BeBaBuA}@w@g@s@cGaF{BaBqD}CqC}BgPgNgQgO{LeLoB_BuM{KyOcNu`@a]sBgBkFmEg@i@?OKY[g@K]Ae@FYLUVQPG`@AXDRLXd@Jl@AbCRz@\\n@lA`ArDlC\\}@jIsSbAgCr@wAr@iAd@o@vAaBxBcB|@k@bAi@lCeAfA]Ka@w@qDC@_EpBqE|BaJrEoAp@_@aBsAaGAKBI"},"summary":"ĐCT20","warnings":[],"waypoint_order":[]}]
     * status : OK
     */

    private String status;
    private List<GeocodedWaypoints> geocoded_waypoints;
    private List<Routes> routes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeocodedWaypoints> getGeocoded_waypoints() {
        return geocoded_waypoints;
    }

    public void setGeocoded_waypoints(List<GeocodedWaypoints> geocoded_waypoints) {
        this.geocoded_waypoints = geocoded_waypoints;
    }

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }

    public static class GeocodedWaypoints {
        /**
         * geocoder_status : OK
         * place_id : ChIJRWo23KKsNTERJl8RDXTGVrc
         * types : ["premise"]
         */

        private String geocoder_status;
        private String place_id;
        private List<String> types;

        public String getGeocoder_status() {
            return geocoder_status;
        }

        public void setGeocoder_status(String geocoder_status) {
            this.geocoder_status = geocoder_status;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }
    }

    public static class Routes {
        /**
         * bounds : {"northeast":{"lat":21.006386,"lng":105.9196732},"southwest":{"lat":20.9638558,"lng":105.8020387}}
         * copyrights : Map data ©2018 Google
         * legs : [{"distance":{"text":"18.8 km","value":18766},"duration":{"text":"30 mins","value":1801},"end_address":"Unnamed Road, Đông Dư, Gia Lâm, Hà Nội, Vietnam","end_location":{"lat":21.0034255,"lng":105.9190374},"start_address":"15 Lê Văn Thiêm, Nhân Chính, Thanh Xuân, Hồ Chí Minh, Vietnam","start_location":{"lat":21.0016756,"lng":105.8026976},"steps":[{"distance":{"text":"31 m","value":31},"duration":{"text":"1 min","value":10},"end_location":{"lat":21.001516,"lng":105.8024492},"html_instructions":"Head <b>southwest<\/b><div style=\"font-size:0.9em\">Restricted usage road<\/div>","polyline":{"points":"o{d_C{qwdS^p@"},"start_location":{"lat":21.0016756,"lng":105.8026976},"travel_mode":"DRIVING"},{"distance":{"text":"0.1 km","value":113},"duration":{"text":"1 min","value":60},"end_location":{"lat":21.0007047,"lng":105.8031051},"html_instructions":"Turn <b>left<\/b> at Tạp Chí Nhà Thầu Và Thị Trường Xây Dựng toward <b>Ngụy Như Kon Tum<\/b><div style=\"font-size:0.9em\">Restricted usage road<\/div><div style=\"font-size:0.9em\">Pass by A&amp;S Law (on the left)<\/div>","maneuver":"turn-left","polyline":{"points":"ozd_CipwdSxAiAhAy@"},"start_location":{"lat":21.001516,"lng":105.8024492},"travel_mode":"DRIVING"},{"distance":{"text":"0.1 km","value":139},"duration":{"text":"1 min","value":46},"end_location":{"lat":20.9999496,"lng":105.8020387},"html_instructions":"Turn <b>right<\/b> at Shop Thời Trang Na Na onto <b>Ngụy Như Kon Tum<\/b><div style=\"font-size:0.9em\">Pass by Bánh Mì Mai Phương (on the right)<\/div>","maneuver":"turn-right","polyline":{"points":"kud_CmtwdSZd@v@tA`AxA"},"start_location":{"lat":21.0007047,"lng":105.8031051},"travel_mode":"DRIVING"},{"distance":{"text":"0.8 km","value":812},"duration":{"text":"3 mins","value":186},"end_location":{"lat":20.9941116,"lng":105.8067413},"html_instructions":"Turn <b>left<\/b> at VPĐD Cty Cổ Phần CN Tàu Thuỷ Hoàng Anh Tại HN onto <b>Đ. Nguyễn Tuân<\/b><div style=\"font-size:0.9em\">Pass by Cửa Hàng Sim Thẻ - Đtdđ (on the left in 400&nbsp;m)<\/div>","maneuver":"turn-left","polyline":{"points":"upd_CwmwdS|@o@fAw@t@k@NKn@e@`@YdBqAxBaBJIzCeC~@s@nA_AXSbBoA|AkAz@k@TQVSJIFG"},"start_location":{"lat":20.9999496,"lng":105.8020387},"travel_mode":"DRIVING"},{"distance":{"text":"0.4 km","value":439},"duration":{"text":"2 mins","value":104},"end_location":{"lat":20.9918111,"lng":105.8033207},"html_instructions":"Turn <b>right<\/b> after Dịch Vụ Photocopy - In 378 (on the right)<div style=\"font-size:0.9em\">Pass by Cafe &amp; Thức Ăn Nhanh Hảo Hảo (on the right)<\/div>","maneuver":"turn-right","polyline":{"points":"elc_CckxdSx@nA?F?FBHDFHPP^zFdJNVNVR\\z@rA"},"start_location":{"lat":20.9941116,"lng":105.8067413},"travel_mode":"DRIVING"},{"distance":{"text":"0.3 km","value":347},"duration":{"text":"1 min","value":83},"end_location":{"lat":20.9893519,"lng":105.8045532},"html_instructions":"Turn <b>left<\/b> onto <b>Đường Nguyễn Xiển<\/b><div style=\"font-size:0.9em\">Pass by Công Ty Cổ Phần Đầu Tư Xây Dựng 3d Panel Việt Nam (on the right)<\/div>","maneuver":"turn-left","polyline":{"points":"y}b_CwuwdSz@nAz@o@dBoAZWnBwAdB_AZU"},"start_location":{"lat":20.9918111,"lng":105.8033207},"travel_mode":"DRIVING"},{"distance":{"text":"0.3 km","value":299},"duration":{"text":"1 min","value":36},"end_location":{"lat":20.9872822,"lng":105.8062993},"html_instructions":"Slight <b>left<\/b> at Quán Vịt Phong Béo onto the <b>Đường Cao Tốc<\/b> ramp","polyline":{"points":"mnb_Cm}wdS?IBIHKLKdEkCdBqAt@k@LGJKBC?E@C?E"},"start_location":{"lat":20.9893519,"lng":105.8045532},"travel_mode":"DRIVING"},{"distance":{"text":"10.6 km","value":10637},"duration":{"text":"11 mins","value":678},"end_location":{"lat":20.9817276,"lng":105.8912596},"html_instructions":"Merge onto <b>ĐCT20<\/b>","maneuver":"merge","polyline":{"points":"oab_CkhxdSxB{ApAy@|@k@dAu@tA}@l@c@zI_Gz@i@XSpD_Cp@c@hBsAtJuGlEuCrFuDlDaCdAq@nA}@v@o@x@u@hAkAdAoAxAsBv@qA`AsBv@sBl@kBf@sB`@kBjBqJt@uDLq@x@iDjBsI^oAf@{ApEuKRi@JUz@sBFM^{@R_@n@uAd@aANg@Lc@\\}@\\y@\\u@Xs@Vw@HUX}@Nk@BMDOTkANy@VqAJw@Ho@JuAD{@DkAFiAHgBFy@Fy@D{@FgAB]B]DeAFeA@aA@Y@UBo@BYBY@W@Q?S?]?a@@a@AY@{@?gA?gA?aC?}AA}A@oA?cA?o@AiA@w@AsA?c@A{@?w@?eA?S?y@CkB?_A?gA?y@?[AqA@{@?a@BaADiAFqAJ_AJeARuCb@iF|AeP\\qDd@sE^wDd@cFLeBLmCDqA@kA?_BCkBAkAAoAAeBYaRCgBCiACkA?[GgCIkCEiAGeAQoBQuAQiAUiAScAW}@[gAe@uAQ_@O_@_AoBkAqBmAcB_BkB{AwAiB{AcBoAe@[ECyByAyCkBaBcAeAq@yBwA_BeAgAy@a@]c@_@_A{@{@cAs@}@m@{@o@cAm@iAOUISKQsA}CgB}C{@qAoA_BoEkEc@[aJyHYWiB{AmAcAw@i@cA{@g@c@y@q@}@w@g@s@"},"start_location":{"lat":20.9872822,"lng":105.8062993},"travel_mode":"DRIVING"},{"distance":{"text":"3.5 km","value":3528},"duration":{"text":"4 mins","value":254},"end_location":{"lat":21.0061194,"lng":105.9129801},"html_instructions":"Continue onto <b>QL1A<\/b>","polyline":{"points":"y~`_Ck{heSyE}Di@c@{BaBqD}CgA}@iA_AwAmAqCaCkGmFq@i@aBuAyCkCeA}@eGgF{LeLoB_ByAqA{JiIaJ}HUSUSkC}BiFoE}JuI{AqA[UYUaGgF][[Ym@i@eA}@kFmEg@i@"},"start_location":{"lat":20.9817276,"lng":105.8912596},"travel_mode":"DRIVING"},{"distance":{"text":"0.5 km","value":528},"duration":{"text":"1 min","value":74},"end_location":{"lat":21.0041071,"lng":105.9114712},"html_instructions":"Take the exit toward <b>Đê Long Biên - Xuân Quan<\/b>/<b>Bát Tràng<\/b>/<b>KĐT Ecopark TP Hưng Yên<\/b>","maneuver":"ramp-right","polyline":{"points":"gwe_CccmeS?G?GCKGMOSKSEMEOCS@QBKBMFKDIJKJEFEHARAL?J@LBHBHHLPJRDVDT?VAd@Ah@@ZF^JZLXNTRRHFb@\\JFrCtB"},"start_location":{"lat":21.0061194,"lng":105.9129801},"travel_mode":"DRIVING"},{"distance":{"text":"1.0 km","value":1033},"duration":{"text":"2 mins","value":125},"end_location":{"lat":20.9983214,"lng":105.918607},"html_instructions":"Turn <b>left<\/b> onto <b>ĐT379<\/b><div style=\"font-size:0.9em\">Pass by Mạnh Lốp (on the right)<\/div>","maneuver":"turn-left","polyline":{"points":"uje_CuyleS^VHURg@|A_EfBiEvBkFL]Re@n@aBd@aALU^k@R]LSV[\\c@x@}@l@g@jA{@|@k@bAi@bAc@hAa@fA]"},"start_location":{"lat":21.0041071,"lng":105.9114712},"travel_mode":"DRIVING"},{"distance":{"text":"0.1 km","value":117},"duration":{"text":"1 min","value":22},"end_location":{"lat":20.9986647,"lng":105.9196732},"html_instructions":"Turn <b>left<\/b>","maneuver":"turn-left","polyline":{"points":"ofd_CifneSKa@w@qD"},"start_location":{"lat":20.9983214,"lng":105.918607},"travel_mode":"DRIVING"},{"distance":{"text":"0.5 km","value":535},"duration":{"text":"2 mins","value":90},"end_location":{"lat":21.0028586,"lng":105.9171546},"html_instructions":"Turn <b>left<\/b>","maneuver":"turn-left","polyline":{"points":"shd_C}lneSC@cAh@{BfAkB`AeBz@kB~@{Av@gAh@qAp@aAh@MF"},"start_location":{"lat":20.9986647,"lng":105.9196732},"travel_mode":"DRIVING"},{"distance":{"text":"0.2 km","value":208},"duration":{"text":"1 min","value":33},"end_location":{"lat":21.0034255,"lng":105.9190374},"html_instructions":"Turn <b>right<\/b>","maneuver":"turn-right","polyline":{"points":"{be_Ce}meSCK[uAYiAS_AS}@Qy@AE?E@E@C"},"start_location":{"lat":21.0028586,"lng":105.9171546},"travel_mode":"DRIVING"}],"traffic_speed_entry":[],"via_waypoint":[]}]
         * overview_polyline : {"points":"o{d_C{qwdS^p@xAiAhAy@Zd@xBnDdCgBvCwB~EsDfDoClGwEfE}CRQx@nA?FBPNXlGdKNVb@t@vBbD`D_CjCoB`CuABSVWdEkCdBqAbAs@NO@I?ExB{AnCeBzCsB~MaJbFcDhBsAtJuG`MkIrFsDfCmBbCaCdAoAxAsBv@qA`AsBv@sBl@kBhA_F`DgPfA{EjBsI^oAf@{ApEuK^_AbAaChCsF\\kAz@wBv@iB`@mAr@gCd@eCb@iCTeCJgC`@eHT_EPgFLyBB{A@kL?oIAeGA}ECyFAoF@}AHkCFqAJ_A^{E`CoWbAeKdA{KZsFF}CCkE_@cZKyGQsGMoCc@eEg@sCk@aCaA}Ca@_A_AoBkAqBmAcB_BkB{AwAiB{AiCkB_C}A{LyHgD_CeA}@_A{@oBaC}A_C}@_BUe@sA}CgB}CkCqDoEkEc@[{JqIwD_D{BeBaBuA}@w@g@s@cGaF{BaBqD}CqC}BgPgNgQgO{LeLoB_BuM{KyOcNu`@a]sBgBkFmEg@i@?OKY[g@K]Ae@FYLUVQPG`@AXDRLXd@Jl@AbCRz@\\n@lA`ArDlC\\}@jIsSbAgCr@wAr@iAd@o@vAaBxBcB|@k@bAi@lCeAfA]Ka@w@qDC@_EpBqE|BaJrEoAp@_@aBsAaGAKBI"}
         * summary : ĐCT20
         * warnings : []
         * waypoint_order : []
         */

        private Bounds bounds;
        private String copyrights;
        private OverviewPolyline overview_polyline;
        private String summary;
        private List<Legs> legs;
        private List<?> warnings;
        private List<?> waypoint_order;

        public Bounds getBounds() {
            return bounds;
        }

        public void setBounds(Bounds bounds) {
            this.bounds = bounds;
        }

        public String getCopyrights() {
            return copyrights;
        }

        public void setCopyrights(String copyrights) {
            this.copyrights = copyrights;
        }

        public OverviewPolyline getOverview_polyline() {
            return overview_polyline;
        }

        public void setOverview_polyline(OverviewPolyline overview_polyline) {
            this.overview_polyline = overview_polyline;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<Legs> getLegs() {
            return legs;
        }

        public void setLegs(List<Legs> legs) {
            this.legs = legs;
        }

        public List<?> getWarnings() {
            return warnings;
        }

        public void setWarnings(List<?> warnings) {
            this.warnings = warnings;
        }

        public List<?> getWaypoint_order() {
            return waypoint_order;
        }

        public void setWaypoint_order(List<?> waypoint_order) {
            this.waypoint_order = waypoint_order;
        }

        public static class Bounds {
            /**
             * northeast : {"lat":21.006386,"lng":105.9196732}
             * southwest : {"lat":20.9638558,"lng":105.8020387}
             */

            private Northeast northeast;
            private Southwest southwest;

            public Northeast getNortheast() {
                return northeast;
            }

            public void setNortheast(Northeast northeast) {
                this.northeast = northeast;
            }

            public Southwest getSouthwest() {
                return southwest;
            }

            public void setSouthwest(Southwest southwest) {
                this.southwest = southwest;
            }

            public static class Northeast {
                /**
                 * lat : 21.006386
                 * lng : 105.9196732
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class Southwest {
                /**
                 * lat : 20.9638558
                 * lng : 105.8020387
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }

        public static class OverviewPolyline {
            /**
             * points : o{d_C{qwdS^p@xAiAhAy@Zd@xBnDdCgBvCwB~EsDfDoClGwEfE}CRQx@nA?FBPNXlGdKNVb@t@vBbD`D_CjCoB`CuABSVWdEkCdBqAbAs@NO@I?ExB{AnCeBzCsB~MaJbFcDhBsAtJuG`MkIrFsDfCmBbCaCdAoAxAsBv@qA`AsBv@sBl@kBhA_F`DgPfA{EjBsI^oAf@{ApEuK^_AbAaChCsF\kAz@wBv@iB`@mAr@gCd@eCb@iCTeCJgC`@eHT_EPgFLyBB{A@kL?oIAeGA}ECyFAoF@}AHkCFqAJ_A^{E`CoWbAeKdA{KZsFF}CCkE_@cZKyGQsGMoCc@eEg@sCk@aCaA}Ca@_A_AoBkAqBmAcB_BkB{AwAiB{AiCkB_C}A{LyHgD_CeA}@_A{@oBaC}A_C}@_BUe@sA}CgB}CkCqDoEkEc@[{JqIwD_D{BeBaBuA}@w@g@s@cGaF{BaBqD}CqC}BgPgNgQgO{LeLoB_BuM{KyOcNu`@a]sBgBkFmEg@i@?OKY[g@K]Ae@FYLUVQPG`@AXDRLXd@Jl@AbCRz@\n@lA`ArDlC\}@jIsSbAgCr@wAr@iAd@o@vAaBxBcB|@k@bAi@lCeAfA]Ka@w@qDC@_EpBqE|BaJrEoAp@_@aBsAaGAKBI
             */

            private String points;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }
        }

        public static class Legs {
            /**
             * distance : {"text":"18.8 km","value":18766}
             * duration : {"text":"30 mins","value":1801}
             * end_address : Unnamed Road, Đông Dư, Gia Lâm, Hà Nội, Vietnam
             * end_location : {"lat":21.0034255,"lng":105.9190374}
             * start_address : 15 Lê Văn Thiêm, Nhân Chính, Thanh Xuân, Hồ Chí Minh, Vietnam
             * start_location : {"lat":21.0016756,"lng":105.8026976}
             * steps : [{"distance":{"text":"31 m","value":31},"duration":{"text":"1 min","value":10},"end_location":{"lat":21.001516,"lng":105.8024492},"html_instructions":"Head <b>southwest<\/b><div style=\"font-size:0.9em\">Restricted usage road<\/div>","polyline":{"points":"o{d_C{qwdS^p@"},"start_location":{"lat":21.0016756,"lng":105.8026976},"travel_mode":"DRIVING"},{"distance":{"text":"0.1 km","value":113},"duration":{"text":"1 min","value":60},"end_location":{"lat":21.0007047,"lng":105.8031051},"html_instructions":"Turn <b>left<\/b> at Tạp Chí Nhà Thầu Và Thị Trường Xây Dựng toward <b>Ngụy Như Kon Tum<\/b><div style=\"font-size:0.9em\">Restricted usage road<\/div><div style=\"font-size:0.9em\">Pass by A&amp;S Law (on the left)<\/div>","maneuver":"turn-left","polyline":{"points":"ozd_CipwdSxAiAhAy@"},"start_location":{"lat":21.001516,"lng":105.8024492},"travel_mode":"DRIVING"},{"distance":{"text":"0.1 km","value":139},"duration":{"text":"1 min","value":46},"end_location":{"lat":20.9999496,"lng":105.8020387},"html_instructions":"Turn <b>right<\/b> at Shop Thời Trang Na Na onto <b>Ngụy Như Kon Tum<\/b><div style=\"font-size:0.9em\">Pass by Bánh Mì Mai Phương (on the right)<\/div>","maneuver":"turn-right","polyline":{"points":"kud_CmtwdSZd@v@tA`AxA"},"start_location":{"lat":21.0007047,"lng":105.8031051},"travel_mode":"DRIVING"},{"distance":{"text":"0.8 km","value":812},"duration":{"text":"3 mins","value":186},"end_location":{"lat":20.9941116,"lng":105.8067413},"html_instructions":"Turn <b>left<\/b> at VPĐD Cty Cổ Phần CN Tàu Thuỷ Hoàng Anh Tại HN onto <b>Đ. Nguyễn Tuân<\/b><div style=\"font-size:0.9em\">Pass by Cửa Hàng Sim Thẻ - Đtdđ (on the left in 400&nbsp;m)<\/div>","maneuver":"turn-left","polyline":{"points":"upd_CwmwdS|@o@fAw@t@k@NKn@e@`@YdBqAxBaBJIzCeC~@s@nA_AXSbBoA|AkAz@k@TQVSJIFG"},"start_location":{"lat":20.9999496,"lng":105.8020387},"travel_mode":"DRIVING"},{"distance":{"text":"0.4 km","value":439},"duration":{"text":"2 mins","value":104},"end_location":{"lat":20.9918111,"lng":105.8033207},"html_instructions":"Turn <b>right<\/b> after Dịch Vụ Photocopy - In 378 (on the right)<div style=\"font-size:0.9em\">Pass by Cafe &amp; Thức Ăn Nhanh Hảo Hảo (on the right)<\/div>","maneuver":"turn-right","polyline":{"points":"elc_CckxdSx@nA?F?FBHDFHPP^zFdJNVNVR\\z@rA"},"start_location":{"lat":20.9941116,"lng":105.8067413},"travel_mode":"DRIVING"},{"distance":{"text":"0.3 km","value":347},"duration":{"text":"1 min","value":83},"end_location":{"lat":20.9893519,"lng":105.8045532},"html_instructions":"Turn <b>left<\/b> onto <b>Đường Nguyễn Xiển<\/b><div style=\"font-size:0.9em\">Pass by Công Ty Cổ Phần Đầu Tư Xây Dựng 3d Panel Việt Nam (on the right)<\/div>","maneuver":"turn-left","polyline":{"points":"y}b_CwuwdSz@nAz@o@dBoAZWnBwAdB_AZU"},"start_location":{"lat":20.9918111,"lng":105.8033207},"travel_mode":"DRIVING"},{"distance":{"text":"0.3 km","value":299},"duration":{"text":"1 min","value":36},"end_location":{"lat":20.9872822,"lng":105.8062993},"html_instructions":"Slight <b>left<\/b> at Quán Vịt Phong Béo onto the <b>Đường Cao Tốc<\/b> ramp","polyline":{"points":"mnb_Cm}wdS?IBIHKLKdEkCdBqAt@k@LGJKBC?E@C?E"},"start_location":{"lat":20.9893519,"lng":105.8045532},"travel_mode":"DRIVING"},{"distance":{"text":"10.6 km","value":10637},"duration":{"text":"11 mins","value":678},"end_location":{"lat":20.9817276,"lng":105.8912596},"html_instructions":"Merge onto <b>ĐCT20<\/b>","maneuver":"merge","polyline":{"points":"oab_CkhxdSxB{ApAy@|@k@dAu@tA}@l@c@zI_Gz@i@XSpD_Cp@c@hBsAtJuGlEuCrFuDlDaCdAq@nA}@v@o@x@u@hAkAdAoAxAsBv@qA`AsBv@sBl@kBf@sB`@kBjBqJt@uDLq@x@iDjBsI^oAf@{ApEuKRi@JUz@sBFM^{@R_@n@uAd@aANg@Lc@\\}@\\y@\\u@Xs@Vw@HUX}@Nk@BMDOTkANy@VqAJw@Ho@JuAD{@DkAFiAHgBFy@Fy@D{@FgAB]B]DeAFeA@aA@Y@UBo@BYBY@W@Q?S?]?a@@a@AY@{@?gA?gA?aC?}AA}A@oA?cA?o@AiA@w@AsA?c@A{@?w@?eA?S?y@CkB?_A?gA?y@?[AqA@{@?a@BaADiAFqAJ_AJeARuCb@iF|AeP\\qDd@sE^wDd@cFLeBLmCDqA@kA?_BCkBAkAAoAAeBYaRCgBCiACkA?[GgCIkCEiAGeAQoBQuAQiAUiAScAW}@[gAe@uAQ_@O_@_AoBkAqBmAcB_BkB{AwAiB{AcBoAe@[ECyByAyCkBaBcAeAq@yBwA_BeAgAy@a@]c@_@_A{@{@cAs@}@m@{@o@cAm@iAOUISKQsA}CgB}C{@qAoA_BoEkEc@[aJyHYWiB{AmAcAw@i@cA{@g@c@y@q@}@w@g@s@"},"start_location":{"lat":20.9872822,"lng":105.8062993},"travel_mode":"DRIVING"},{"distance":{"text":"3.5 km","value":3528},"duration":{"text":"4 mins","value":254},"end_location":{"lat":21.0061194,"lng":105.9129801},"html_instructions":"Continue onto <b>QL1A<\/b>","polyline":{"points":"y~`_Ck{heSyE}Di@c@{BaBqD}CgA}@iA_AwAmAqCaCkGmFq@i@aBuAyCkCeA}@eGgF{LeLoB_ByAqA{JiIaJ}HUSUSkC}BiFoE}JuI{AqA[UYUaGgF][[Ym@i@eA}@kFmEg@i@"},"start_location":{"lat":20.9817276,"lng":105.8912596},"travel_mode":"DRIVING"},{"distance":{"text":"0.5 km","value":528},"duration":{"text":"1 min","value":74},"end_location":{"lat":21.0041071,"lng":105.9114712},"html_instructions":"Take the exit toward <b>Đê Long Biên - Xuân Quan<\/b>/<b>Bát Tràng<\/b>/<b>KĐT Ecopark TP Hưng Yên<\/b>","maneuver":"ramp-right","polyline":{"points":"gwe_CccmeS?G?GCKGMOSKSEMEOCS@QBKBMFKDIJKJEFEHARAL?J@LBHBHHLPJRDVDT?VAd@Ah@@ZF^JZLXNTRRHFb@\\JFrCtB"},"start_location":{"lat":21.0061194,"lng":105.9129801},"travel_mode":"DRIVING"},{"distance":{"text":"1.0 km","value":1033},"duration":{"text":"2 mins","value":125},"end_location":{"lat":20.9983214,"lng":105.918607},"html_instructions":"Turn <b>left<\/b> onto <b>ĐT379<\/b><div style=\"font-size:0.9em\">Pass by Mạnh Lốp (on the right)<\/div>","maneuver":"turn-left","polyline":{"points":"uje_CuyleS^VHURg@|A_EfBiEvBkFL]Re@n@aBd@aALU^k@R]LSV[\\c@x@}@l@g@jA{@|@k@bAi@bAc@hAa@fA]"},"start_location":{"lat":21.0041071,"lng":105.9114712},"travel_mode":"DRIVING"},{"distance":{"text":"0.1 km","value":117},"duration":{"text":"1 min","value":22},"end_location":{"lat":20.9986647,"lng":105.9196732},"html_instructions":"Turn <b>left<\/b>","maneuver":"turn-left","polyline":{"points":"ofd_CifneSKa@w@qD"},"start_location":{"lat":20.9983214,"lng":105.918607},"travel_mode":"DRIVING"},{"distance":{"text":"0.5 km","value":535},"duration":{"text":"2 mins","value":90},"end_location":{"lat":21.0028586,"lng":105.9171546},"html_instructions":"Turn <b>left<\/b>","maneuver":"turn-left","polyline":{"points":"shd_C}lneSC@cAh@{BfAkB`AeBz@kB~@{Av@gAh@qAp@aAh@MF"},"start_location":{"lat":20.9986647,"lng":105.9196732},"travel_mode":"DRIVING"},{"distance":{"text":"0.2 km","value":208},"duration":{"text":"1 min","value":33},"end_location":{"lat":21.0034255,"lng":105.9190374},"html_instructions":"Turn <b>right<\/b>","maneuver":"turn-right","polyline":{"points":"{be_Ce}meSCK[uAYiAS_AS}@Qy@AE?E@E@C"},"start_location":{"lat":21.0028586,"lng":105.9171546},"travel_mode":"DRIVING"}]
             * traffic_speed_entry : []
             * via_waypoint : []
             */

            private Distance distance;
            private Duration duration;
            private String end_address;
            private EndLocation end_location;
            private String start_address;
            private StartLocation start_location;
            private List<Steps> steps;
            private List<?> traffic_speed_entry;
            private List<?> via_waypoint;

            public Distance getDistance() {
                return distance;
            }

            public void setDistance(Distance distance) {
                this.distance = distance;
            }

            public Duration getDuration() {
                return duration;
            }

            public void setDuration(Duration duration) {
                this.duration = duration;
            }

            public String getEnd_address() {
                return end_address;
            }

            public void setEnd_address(String end_address) {
                this.end_address = end_address;
            }

            public EndLocation getEnd_location() {
                return end_location;
            }

            public void setEnd_location(EndLocation end_location) {
                this.end_location = end_location;
            }

            public String getStart_address() {
                return start_address;
            }

            public void setStart_address(String start_address) {
                this.start_address = start_address;
            }

            public StartLocation getStart_location() {
                return start_location;
            }

            public void setStart_location(StartLocation start_location) {
                this.start_location = start_location;
            }

            public List<Steps> getSteps() {
                return steps;
            }

            public void setSteps(List<Steps> steps) {
                this.steps = steps;
            }

            public List<?> getTraffic_speed_entry() {
                return traffic_speed_entry;
            }

            public void setTraffic_speed_entry(List<?> traffic_speed_entry) {
                this.traffic_speed_entry = traffic_speed_entry;
            }

            public List<?> getVia_waypoint() {
                return via_waypoint;
            }

            public void setVia_waypoint(List<?> via_waypoint) {
                this.via_waypoint = via_waypoint;
            }

            public static class Distance {
                /**
                 * text : 18.8 km
                 * value : 18766
                 */

                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public static class Duration {
                /**
                 * text : 30 mins
                 * value : 1801
                 */

                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public static class EndLocation {
                /**
                 * lat : 21.0034255
                 * lng : 105.9190374
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class StartLocation {
                /**
                 * lat : 21.0016756
                 * lng : 105.8026976
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class Steps {
                /**
                 * distance : {"text":"31 m","value":31}
                 * duration : {"text":"1 min","value":10}
                 * end_location : {"lat":21.001516,"lng":105.8024492}
                 * html_instructions : Head <b>southwest</b><div style="font-size:0.9em">Restricted usage road</div>
                 * polyline : {"points":"o{d_C{qwdS^p@"}
                 * start_location : {"lat":21.0016756,"lng":105.8026976}
                 * travel_mode : DRIVING
                 * maneuver : turn-left
                 */

                private DistanceX distance;
                private DurationX duration;
                private EndLocationX end_location;
                private String html_instructions;
                private Polyline polyline;
                private StartLocationX start_location;
                private String travel_mode;
                private String maneuver;

                public DistanceX getDistance() {
                    return distance;
                }

                public void setDistance(DistanceX distance) {
                    this.distance = distance;
                }

                public DurationX getDuration() {
                    return duration;
                }

                public void setDuration(DurationX duration) {
                    this.duration = duration;
                }

                public EndLocationX getEnd_location() {
                    return end_location;
                }

                public void setEnd_location(EndLocationX end_location) {
                    this.end_location = end_location;
                }

                public String getHtml_instructions() {
                    return html_instructions;
                }

                public void setHtml_instructions(String html_instructions) {
                    this.html_instructions = html_instructions;
                }

                public Polyline getPolyline() {
                    return polyline;
                }

                public void setPolyline(Polyline polyline) {
                    this.polyline = polyline;
                }

                public StartLocationX getStart_location() {
                    return start_location;
                }

                public void setStart_location(StartLocationX start_location) {
                    this.start_location = start_location;
                }

                public String getTravel_mode() {
                    return travel_mode;
                }

                public void setTravel_mode(String travel_mode) {
                    this.travel_mode = travel_mode;
                }

                public String getManeuver() {
                    return maneuver;
                }

                public void setManeuver(String maneuver) {
                    this.maneuver = maneuver;
                }

                public static class DistanceX {
                    /**
                     * text : 31 m
                     * value : 31
                     */

                    private String text;
                    private int value;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public int getValue() {
                        return value;
                    }

                    public void setValue(int value) {
                        this.value = value;
                    }
                }

                public static class DurationX {
                    /**
                     * text : 1 min
                     * value : 10
                     */

                    private String text;
                    private int value;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public int getValue() {
                        return value;
                    }

                    public void setValue(int value) {
                        this.value = value;
                    }
                }

                public static class EndLocationX {
                    /**
                     * lat : 21.001516
                     * lng : 105.8024492
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class Polyline {
                    /**
                     * points : o{d_C{qwdS^p@
                     */

                    private String points;

                    public String getPoints() {
                        return points;
                    }

                    public void setPoints(String points) {
                        this.points = points;
                    }
                }

                public static class StartLocationX {
                    /**
                     * lat : 21.0016756
                     * lng : 105.8026976
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }
    }
}

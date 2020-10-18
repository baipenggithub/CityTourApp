package com.bp.hmi.citytour.bean;

import java.io.Serializable;

/**
 * <pre>
 *     author : BaiPengMac
 *     e-mail : xxx@xx
 *     time   : 2020/10/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class EnterShDetailsBean implements Serializable {

    /**
     * code : 1
     * msg : 操作成功
     * result : {"id":2,"name":"中共一大会址纪念馆","recommendPoint":1,"spendTime":2,"cover":"uploadCityWalk/zouJinSH/1b197b3e7aea45a98a5ccd5ca9855445.png","summary":"景点地址：上海市黄浦区黄陂南路374号\r\n开放时间：09:00-17:00开放（16:00停止入园）\r\n官方电话：021-53832171","content":"<p class=\"MsoNormal\" style=\"text-align: justify;\"><span><font face=\"宋体\">中共一大会址是中国共产党的建党之地，<\/font>1921<font face=\"宋体\">年<\/font><font face=\"等线\">7<\/font><font face=\"宋体\">月，中国共产党第一次全国代表大会在此召开。如今这里还原了当年开会时的家具摆设，同时展示有大量的历史资料和人物介绍。<\/font><\/span><span><o:p><\/o:p><\/span><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><span><font face=\"宋体\">会址是典型的上海石库门老建筑，可见到青红交错的砖墙、一扇扇挂着铜环的黑漆大门。纪念馆的入口在兴业路上，在入口处领票、过安检后免费参观，要注意纪念馆内是不能拍照的。纪念馆内有义务讲解员，建议参观时跟随讲解员，可以听到很多生动的历史故事和人物介绍。<\/font><\/span><span><o:p><\/o:p><\/span><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><span><font face=\"宋体\">展厅主要在二楼，这里陈列着家具、个人物品等文物以及文字图片资料，介绍了中国共产党诞生的史迹、历史背景、还有<\/font>\u201c<font face=\"宋体\">一大<\/font><font face=\"等线\">\u201d<\/font><font face=\"宋体\">与会者介绍与各地共产主义小组活动记录。<\/font><\/span><span><o:p><\/o:p><\/span><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><span><font face=\"宋体\">从展厅下到一楼，走过天井可看到一排四开间的小房间，这里就是当年开会的地方，可以看到里面还原的家具摆设和环境布置。参观完之后往出口方向会看到一台参观感言的液晶屏，可以留下你的感言或心愿，出口处在黄陂南路上。<\/font><\/span><span><o:p><\/o:p><\/span><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><font face=\"宋体\">会址紧邻<\/font><a href=\"https://you.ctrip.com/sight/shanghai2/18739.html\"><span><font face=\"宋体\" color=\"#000000\">新天地<\/font><\/span><\/a><font face=\"宋体\">，如有时间还可以去逛逛，体验小资与时尚。<\/font><o:p><\/o:p><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><br><\/p>","createTime":"2020-09-13 18:58:18"}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * id : 2
         * name : 中共一大会址纪念馆
         * recommendPoint : 1
         * spendTime : 2
         * cover : uploadCityWalk/zouJinSH/1b197b3e7aea45a98a5ccd5ca9855445.png
         * summary : 景点地址：上海市黄浦区黄陂南路374号
         开放时间：09:00-17:00开放（16:00停止入园）
         官方电话：021-53832171
         * content : <p class="MsoNormal" style="text-align: justify;"><span><font face="宋体">中共一大会址是中国共产党的建党之地，</font>1921<font face="宋体">年</font><font face="等线">7</font><font face="宋体">月，中国共产党第一次全国代表大会在此召开。如今这里还原了当年开会时的家具摆设，同时展示有大量的历史资料和人物介绍。</font></span><span><o:p></o:p></span></p><p class="MsoNormal" style="text-align: justify;"><span><font face="宋体">会址是典型的上海石库门老建筑，可见到青红交错的砖墙、一扇扇挂着铜环的黑漆大门。纪念馆的入口在兴业路上，在入口处领票、过安检后免费参观，要注意纪念馆内是不能拍照的。纪念馆内有义务讲解员，建议参观时跟随讲解员，可以听到很多生动的历史故事和人物介绍。</font></span><span><o:p></o:p></span></p><p class="MsoNormal" style="text-align: justify;"><span><font face="宋体">展厅主要在二楼，这里陈列着家具、个人物品等文物以及文字图片资料，介绍了中国共产党诞生的史迹、历史背景、还有</font>“<font face="宋体">一大</font><font face="等线">”</font><font face="宋体">与会者介绍与各地共产主义小组活动记录。</font></span><span><o:p></o:p></span></p><p class="MsoNormal" style="text-align: justify;"><span><font face="宋体">从展厅下到一楼，走过天井可看到一排四开间的小房间，这里就是当年开会的地方，可以看到里面还原的家具摆设和环境布置。参观完之后往出口方向会看到一台参观感言的液晶屏，可以留下你的感言或心愿，出口处在黄陂南路上。</font></span><span><o:p></o:p></span></p><p class="MsoNormal" style="text-align: justify;"><font face="宋体">会址紧邻</font><a href="https://you.ctrip.com/sight/shanghai2/18739.html"><span><font face="宋体" color="#000000">新天地</font></span></a><font face="宋体">，如有时间还可以去逛逛，体验小资与时尚。</font><o:p></o:p></p><p class="MsoNormal" style="text-align: justify;"><br></p>
         * createTime : 2020-09-13 18:58:18
         */

        private int id;
        private String name;
        private int recommendPoint;
        private int spendTime;
        private String cover;
        private String summary;
        private String content;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRecommendPoint() {
            return recommendPoint;
        }

        public void setRecommendPoint(int recommendPoint) {
            this.recommendPoint = recommendPoint;
        }

        public int getSpendTime() {
            return spendTime;
        }

        public void setSpendTime(int spendTime) {
            this.spendTime = spendTime;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}

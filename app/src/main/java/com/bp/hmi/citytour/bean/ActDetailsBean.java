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
public class ActDetailsBean implements Serializable {


    /**
     * code : 1
     * msg : 操作成功
     * result : {"id":26,"name":"安义夜巷","cover":"uploadCityWalk/activity/dc9697a67d0f400bb7b352afd66de086.png","summary":"每周六、周日16:00-23:00静安区静安嘉里中心安义路","content":"<p class=\"MsoNormal\" style=\"text-align: justify;\"><span><font face=\"宋体\">如果评选今年夏天最受欢迎的夜市，安义夜巷一定榜上有名，因为实在太火，每次去打卡都要提前养精蓄锐备好充足体力，做好排队的准备。<\/font><\/span><span><br><\/span><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><br><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><b><span>8<font face=\"宋体\">月开始，安义夜巷也换上夏日新装，变身纳凉胜地。<\/font><\/span><\/b><span><font face=\"宋体\">夜市里的小店也纷纷展示夏日风情，椰林、沙滩、尤克里里，仿佛真的来到了夏威夷，连空气中都弥漫着夏天的味道。<\/font><\/span><span><o:p><\/o:p><\/span><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><br><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><span><font face=\"宋体\">同步解锁夏日专属美食！炎炎夏日续命必不可少的刨冰，小编推荐夜市上<\/font>HEFA COFFEE<font face=\"宋体\">家的草莓可尔必思咖啡刨冰，清凉解暑不腻说的就是它！还有<\/font><font face=\"等线\">ORO<\/font><font face=\"宋体\">带上<\/font><font face=\"等线\">\u201c<\/font><font face=\"宋体\">安义夜巷<\/font><font face=\"等线\">\u201d<\/font><font face=\"宋体\">路牌吸管的莫吉托，颜值有点高哦。<\/font><\/span><span><br><\/span><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><br><\/p><p class=\"MsoNormal\" style=\"text-align: justify;\"><span><font face=\"宋体\">夏天又怎么能少得了户外音乐，给本就热火朝天的夜晚带来更高一重的热度，跟着人群一起和乐队狂嗨才是这夏天正确的打开方式嘛<\/font>~<\/span><span><o:p><\/o:p><\/span><\/p>","pingfen":"","time":"","address":"静安区静安嘉里中心安义路","type":6,"isNew":0,"createTime":"2020-09-13 22:17:35"}
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

    public static class ResultBean implements Serializable{
        /**
         * id : 26
         * name : 安义夜巷
         * cover : uploadCityWalk/activity/dc9697a67d0f400bb7b352afd66de086.png
         * summary : 每周六、周日16:00-23:00静安区静安嘉里中心安义路
         * content : <p class="MsoNormal" style="text-align: justify;"><span><font face="宋体">如果评选今年夏天最受欢迎的夜市，安义夜巷一定榜上有名，因为实在太火，每次去打卡都要提前养精蓄锐备好充足体力，做好排队的准备。</font></span><span><br></span></p><p class="MsoNormal" style="text-align: justify;"><br></p><p class="MsoNormal" style="text-align: justify;"><b><span>8<font face="宋体">月开始，安义夜巷也换上夏日新装，变身纳凉胜地。</font></span></b><span><font face="宋体">夜市里的小店也纷纷展示夏日风情，椰林、沙滩、尤克里里，仿佛真的来到了夏威夷，连空气中都弥漫着夏天的味道。</font></span><span><o:p></o:p></span></p><p class="MsoNormal" style="text-align: justify;"><br></p><p class="MsoNormal" style="text-align: justify;"><span><font face="宋体">同步解锁夏日专属美食！炎炎夏日续命必不可少的刨冰，小编推荐夜市上</font>HEFA COFFEE<font face="宋体">家的草莓可尔必思咖啡刨冰，清凉解暑不腻说的就是它！还有</font><font face="等线">ORO</font><font face="宋体">带上</font><font face="等线">“</font><font face="宋体">安义夜巷</font><font face="等线">”</font><font face="宋体">路牌吸管的莫吉托，颜值有点高哦。</font></span><span><br></span></p><p class="MsoNormal" style="text-align: justify;"><br></p><p class="MsoNormal" style="text-align: justify;"><span><font face="宋体">夏天又怎么能少得了户外音乐，给本就热火朝天的夜晚带来更高一重的热度，跟着人群一起和乐队狂嗨才是这夏天正确的打开方式嘛</font>~</span><span><o:p></o:p></span></p>
         * pingfen :
         * time :
         * address : 静安区静安嘉里中心安义路
         * type : 6
         * isNew : 0
         * createTime : 2020-09-13 22:17:35
         */

        private int id;
        private String name;
        private String cover;
        private String summary;
        private String content;
        private String pingfen;
        private String time;
        private String address;
        private int type;
        private int isNew;
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

        public String getPingfen() {
            return pingfen;
        }

        public void setPingfen(String pingfen) {
            this.pingfen = pingfen;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIsNew() {
            return isNew;
        }

        public void setIsNew(int isNew) {
            this.isNew = isNew;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}

package com.example.administrator.newsdemo.bean;

import java.util.List;

/**
 * 类的用途：
 * Created by Administrator on 2017/3/11.
 */

public class Info {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"9e626a566da849bb771de7ddcfd35591","title":"苏富比在纽约启动亚洲艺术周展拍","date":"2017-03-11 08:18","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311081853185.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311081853_9b401de4f78b6cdbdc177ecc278a6408_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170311/20170311081853_7159118301e17072929e05136ef3fb30_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170311/20170311081853_8af4fb830c0e68536311f61f70e06bf5_3_mwpm_03200403.jpeg"},{"uniquekey":"314da093906b1360bddd11a48d7044ab","title":"欧盟春季峰会闭幕","date":"2017-03-11 08:18","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311081850658.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170311/20170311081850_2b18e8630188a37fff93c95a8c876b1f_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20170311/20170311081850_b01c7bdfb1fa296c5381d5d65acfb62a_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20170311/20170311081850_ee40d670faf4bf9405568e6fa89cdb37_3_mwpm_03200403.jpeg"},{"uniquekey":"72657bfee193946304cba9f53dbeea1b","title":"韩国欲起诉中国反\u201c萨德\u201d！天大的笑话","date":"2017-03-11 08:15","category":"国际","author_name":"北京日报","url":"http://mini.eastday.com/mobile/170311081556962.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170311/20170311081556_c59c3960bc01d112490dddb654f18fbf_1_mwpm_03200403.jpeg"},{"uniquekey":"95114c88b3fc5300928f9161e72e67a6","title":"伊政府军目标一个月拿下摩苏尔城西","date":"2017-03-11 08:13","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311081317166.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170311/20170311081317_9f53c32a665f44d4138eb57fcc2d3124_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170311/20170311081317_920cb194fa6873c09de112063ff2107c_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170311/20170311081317_b293f630f0a4300cbeaeb5e66ffabb91_3_mwpm_03200403.jpeg"},{"uniquekey":"7f7bb8d586dbad6b12d900ed74d73d60","title":"泰国老虎发春困 笼中打盹似\u201c萌猫\u201d","date":"2017-03-11 08:08","category":"国际","author_name":"环球网","url":"http://mini.eastday.com/mobile/170311080857648.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170311/20170311080857_a98d6e94b34d5be08e05519a64c10ba2_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170311/20170311080857_a98d6e94b34d5be08e05519a64c10ba2_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170311/20170311080857_a98d6e94b34d5be08e05519a64c10ba2_3_mwpm_03200403.jpeg"},{"uniquekey":"b09d280f9e8f7e8c37dc1c68f837221d","title":"沙尘暴袭击阿勒颇 昔日文化遗产如鬼城","date":"2017-03-11 08:05","category":"国际","author_name":"内幕君","url":"http://mini.eastday.com/mobile/170311080519214.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311080519_ce8041180799b3a535151a710c77e101_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170311/20170311080519_ce8041180799b3a535151a710c77e101_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170311/20170311080519_ce8041180799b3a535151a710c77e101_3_mwpm_03200403.jpeg"},{"uniquekey":"a34d060c0c62e27b16a890307eb0876f","title":"伦敦法院审理马来西亚史上最贵离婚案","date":"2017-03-11 07:40","category":"国际","author_name":"北京时间综合","url":"http://mini.eastday.com/mobile/170311074041828.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170311/20170311074041_6e6e2d830716fc6cd34829ae1682368f_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170311/20170311074041_6e6e2d830716fc6cd34829ae1682368f_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170311/20170311074041_6e6e2d830716fc6cd34829ae1682368f_3_mwpm_03200403.jpeg"},{"uniquekey":"5e4b2b646a77d6447993bf1a3a7d3369","title":"美国6州挑战特朗普 继续暂停实施2.0版移民禁令","date":"2017-03-11 07:31","category":"国际","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170311073153827.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170311/20170311073153_3157e5d93269cef269860985174ff2b8_1_mwpm_03200403.jpeg"},{"uniquekey":"99ad6ed0d4d67af51011695cf0002fe6","title":"美特种部队支援进攻\u201c伊斯兰国\u201d战略要冲","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064625629.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170311/20170311064625_e2263649da51e3fa23c1847b1c4645e4_1_mwpm_03200403.jpeg"},{"uniquekey":"ba758ba57eb65d443591bf818fc24ce2","title":"欧洲建材巨头不顾压力 分美墨隔离墙\u201c蛋糕\u201d","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064625286.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170311/20170311064625_050c19dc1f63d7980a01147272027d22_1_mwpm_03200403.jpeg"},{"uniquekey":"6e60a287047e7cde12e15d7e683eda7b","title":"想在\u201c伦敦眼\u201d上过夜吗？你的机会来了！","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064625217.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170311/20170311064625_6ab7de1d4b9c3bda08d162af3a2e78aa_1_mwpm_03200403.jpeg"},{"uniquekey":"ec8fd52695051cb514825fd422f679cd","title":"法国总统选举在即 马克龙不断巩固领先地位","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064624907.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170311/20170311064624_14e4edf9338b8a4bf71fbe59cf300bfb_1_mwpm_03200403.jpeg"},{"uniquekey":"03bc23556f31283f7dd14ff47c666639","title":"印度：将\u201c庸医\u201d变\u201c祸\u201d为宝 效果喜忧参半","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064624846.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170311/20170311064624_48b9701047ada01ede429e0bfb12c022_1_mwpm_03200403.jpeg"},{"uniquekey":"18a41e3901a940c7da6f2e0a1d8bdfd5","title":"德国再发生斧头袭击事件 7人受伤 嫌疑人被捕","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064624706.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170311/20170311064624_d93e491c42abfb3e48ae502a0bc57389_1_mwpm_03200403.jpeg"},{"uniquekey":"5779c8b2214d3e2aa9deb33658829009","title":"提供窃听资料 阿桑奇欲为科技公司\u201c开小灶\u201d","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064624556.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170311/20170311064624_7b078b8d882a88c61918891406e6edb6_1_mwpm_03200403.jpeg"},{"uniquekey":"5c8626dfcb4a2c8b8c86b69fda810a46","title":"笑喷！南非河马泡\u201c无边泳池\u201d享受悠闲假日","date":"2017-03-11 06:46","category":"国际","author_name":"新华网","url":"http://mini.eastday.com/mobile/170311064624253.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170311/20170311064624_faeb2f27c59bca8a9e8d2821713fd824_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170311/20170311064624_91e96034f928385067b93fd3620ae777_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170311/20170311064624_006769a20aca9d2237d282e56d9508cd_3_mwpm_03200403.jpeg"},{"uniquekey":"666ef9f6acc03eec1241d79f7eda1a34","title":"\u201c亲信干政\u201d案后 将有哪些人走上被告席？","date":"2017-03-11 06:45","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064534983.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170311/20170311064534_623b25d1807acfeea2bd5288e65578d6_1_mwpm_03200403.jpeg"},{"uniquekey":"ecffd9873f4d895cf93ab9f9ebc4b313","title":"设得兰小马喜欢双腿站立跳舞 自带\u201c派对女王\u201d属性","date":"2017-03-11 06:42","category":"国际","author_name":"新华网","url":"http://mini.eastday.com/mobile/170311064252761.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_45fadfc47a0fedc6b3bfd77dd3e35b17_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_0c94c0c7def99cdd229e15ad17a08ad5_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_f63ca54314597c70591f3d53dd367722_3_mwpm_03200403.jpeg"},{"uniquekey":"102bf594cb65889526c464ef137b4465","title":"英国克鲁弗兹狗展持续举行 憨萌名犬亮相比拼","date":"2017-03-11 06:42","category":"国际","author_name":"新华网","url":"http://mini.eastday.com/mobile/170311064252266.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_e4ed26db530812097ae81cd0ab9252c6_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_1943bd1779d0d376a49a03e8b4f99333_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_fb424614db9622b67e153e440667b5e8_3_mwpm_03200403.jpeg"},{"uniquekey":"ed7c9f110cd6d6f2ac5aeb1cf15aa720","title":"美国狗狗重76斤险胖死 健身减肥从矮胖墩蜕变成美汪","date":"2017-03-11 06:36","category":"国际","author_name":"新华网","url":"http://mini.eastday.com/mobile/170311063635022.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170311/20170311063635_8d67eea390998efd342f8c3b9859d03e_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170311/20170311063635_32c7727099ac9e432d8bd19b0518b3b2_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170311/20170311063635_3f4b9a48566e70028a5265ae2353cdf0_3_mwpm_03200403.jpeg"},{"uniquekey":"d89ec153d61ca9d014e5b2e12f9a722a","title":"这才是美元下挫元凶？！美高官接连发表惊人言论 美元惨遭两连击","date":"2017-03-11 06:22","category":"国际","author_name":"FX168财经网","url":"http://mini.eastday.com/mobile/170311062213262.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170311/20170311062213_9b13cd0f4bc7ca7873419a97ada5bdf2_1_mwpm_03200403.jpeg"},{"uniquekey":"ae460a9b321f09b00a5abe9e4a123c8a","title":"澳总理回应能源危机问题 承认澳企优势正逐渐丧失","date":"2017-03-11 06:21","category":"国际","author_name":"中新网","url":"http://mini.eastday.com/mobile/170311062100979.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170311/20170311062100_f092d82b9944b91d96224eca438f22b9_1_mwpm_03200403.jpeg"},{"uniquekey":"f9698d767a304eaa711f2c8a4ef40af1","title":"印度狂怼中国却不忘加入上合示好，原来有一事相求不得不低头啊","date":"2017-03-11 06:01","category":"国际","author_name":"第一军情","url":"http://mini.eastday.com/mobile/170311060113084.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170311/20170311060113_2f27d89b2109af4cfc4082d0d62b101a_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20170311/20170311060113_2f27d89b2109af4cfc4082d0d62b101a_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20170311/20170311060113_2f27d89b2109af4cfc4082d0d62b101a_3_mwpm_03200403.jpeg"},{"uniquekey":"c83eccc252381ce718cfb33131269cd8","title":"特朗普正导演能源政策大戏","date":"2017-03-11 05:53","category":"国际","author_name":"人民日报","url":"http://mini.eastday.com/mobile/170311055304779.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170311/20170311055304_e0d633c5a679b85f2174c35b8641bb01_1_mwpm_03200403.jpeg"},{"uniquekey":"41ea6e0c32440359cd319bd668b3f4aa","title":"韩国对中国\u201c出招\u201d？无语！","date":"2017-03-11 05:46","category":"国际","author_name":"人民日报","url":"http://mini.eastday.com/mobile/170311054649208.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170311/20170311054649_6d56b9c37ddd929264b56680b50c0751_1_mwpm_03200403.jpeg"},{"uniquekey":"0dad65648ee9242f76665a7008e17655","title":"朴槿惠被弹劾\u201c挺朴\u201d派民众与警方发生冲突","date":"2017-03-11 05:16","category":"国际","author_name":"光明网-《光明日报》","url":"http://mini.eastday.com/mobile/170311051611964.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170311/20170311051611_ff9bf6444f4dd0ad39c5788c03128e70_1_mwpm_03200403.jpeg"},{"uniquekey":"9eebf1805291538aad59685b38d5e00a","title":"中国驻日大使：中日之间的特点是以民促官","date":"2017-03-11 05:12","category":"国际","author_name":"北京青年报","url":"http://mini.eastday.com/mobile/170311051221399.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311051221_8d5014d6179a3510e2d3954de08f0e46_1_mwpm_03200403.jpeg"},{"uniquekey":"222723867c93096c1ed55f477c91e74d","title":"普京：希望见到俄土关系达到新水平","date":"2017-03-11 04:16","category":"国际","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/170311041633507.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170311/20170311041633_c9ce515d997e28804e765cc22c199195_1_mwpm_03200403.jpeg"},{"uniquekey":"2ab1792dbc6568e630dd3d4ab14128b3","title":"首都用了500年的名字都改了，就为摆脱中国，比日本还要自卑！","date":"2017-03-11 04:12","category":"国际","author_name":"历史大观园","url":"http://mini.eastday.com/mobile/170311041249755.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170311/20170311041249_8f5b7d5c17dc6606ab73baaf870ca2bf_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170311/20170311041249_8f5b7d5c17dc6606ab73baaf870ca2bf_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170311/20170311041249_8f5b7d5c17dc6606ab73baaf870ca2bf_3_mwpm_03200403.jpeg"},{"uniquekey":"9df61982c45dedd0fb45ef0bec42f761","title":"芬兰机场地勤人员举行罢工，上百航班受影响被迫取消","date":"2017-03-11 04:07","category":"国际","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/170311040739494.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170311/20170311040739_95c1a645634d3454ce18994520706382_1_mwpm_03200403.jpeg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "Info{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"9e626a566da849bb771de7ddcfd35591","title":"苏富比在纽约启动亚洲艺术周展拍","date":"2017-03-11 08:18","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311081853185.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311081853_9b401de4f78b6cdbdc177ecc278a6408_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170311/20170311081853_7159118301e17072929e05136ef3fb30_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170311/20170311081853_8af4fb830c0e68536311f61f70e06bf5_3_mwpm_03200403.jpeg"},{"uniquekey":"314da093906b1360bddd11a48d7044ab","title":"欧盟春季峰会闭幕","date":"2017-03-11 08:18","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311081850658.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170311/20170311081850_2b18e8630188a37fff93c95a8c876b1f_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20170311/20170311081850_b01c7bdfb1fa296c5381d5d65acfb62a_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20170311/20170311081850_ee40d670faf4bf9405568e6fa89cdb37_3_mwpm_03200403.jpeg"},{"uniquekey":"72657bfee193946304cba9f53dbeea1b","title":"韩国欲起诉中国反\u201c萨德\u201d！天大的笑话","date":"2017-03-11 08:15","category":"国际","author_name":"北京日报","url":"http://mini.eastday.com/mobile/170311081556962.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170311/20170311081556_c59c3960bc01d112490dddb654f18fbf_1_mwpm_03200403.jpeg"},{"uniquekey":"95114c88b3fc5300928f9161e72e67a6","title":"伊政府军目标一个月拿下摩苏尔城西","date":"2017-03-11 08:13","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311081317166.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170311/20170311081317_9f53c32a665f44d4138eb57fcc2d3124_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170311/20170311081317_920cb194fa6873c09de112063ff2107c_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170311/20170311081317_b293f630f0a4300cbeaeb5e66ffabb91_3_mwpm_03200403.jpeg"},{"uniquekey":"7f7bb8d586dbad6b12d900ed74d73d60","title":"泰国老虎发春困 笼中打盹似\u201c萌猫\u201d","date":"2017-03-11 08:08","category":"国际","author_name":"环球网","url":"http://mini.eastday.com/mobile/170311080857648.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170311/20170311080857_a98d6e94b34d5be08e05519a64c10ba2_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170311/20170311080857_a98d6e94b34d5be08e05519a64c10ba2_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170311/20170311080857_a98d6e94b34d5be08e05519a64c10ba2_3_mwpm_03200403.jpeg"},{"uniquekey":"b09d280f9e8f7e8c37dc1c68f837221d","title":"沙尘暴袭击阿勒颇 昔日文化遗产如鬼城","date":"2017-03-11 08:05","category":"国际","author_name":"内幕君","url":"http://mini.eastday.com/mobile/170311080519214.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311080519_ce8041180799b3a535151a710c77e101_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170311/20170311080519_ce8041180799b3a535151a710c77e101_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170311/20170311080519_ce8041180799b3a535151a710c77e101_3_mwpm_03200403.jpeg"},{"uniquekey":"a34d060c0c62e27b16a890307eb0876f","title":"伦敦法院审理马来西亚史上最贵离婚案","date":"2017-03-11 07:40","category":"国际","author_name":"北京时间综合","url":"http://mini.eastday.com/mobile/170311074041828.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170311/20170311074041_6e6e2d830716fc6cd34829ae1682368f_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170311/20170311074041_6e6e2d830716fc6cd34829ae1682368f_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170311/20170311074041_6e6e2d830716fc6cd34829ae1682368f_3_mwpm_03200403.jpeg"},{"uniquekey":"5e4b2b646a77d6447993bf1a3a7d3369","title":"美国6州挑战特朗普 继续暂停实施2.0版移民禁令","date":"2017-03-11 07:31","category":"国际","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170311073153827.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170311/20170311073153_3157e5d93269cef269860985174ff2b8_1_mwpm_03200403.jpeg"},{"uniquekey":"99ad6ed0d4d67af51011695cf0002fe6","title":"美特种部队支援进攻\u201c伊斯兰国\u201d战略要冲","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064625629.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170311/20170311064625_e2263649da51e3fa23c1847b1c4645e4_1_mwpm_03200403.jpeg"},{"uniquekey":"ba758ba57eb65d443591bf818fc24ce2","title":"欧洲建材巨头不顾压力 分美墨隔离墙\u201c蛋糕\u201d","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064625286.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170311/20170311064625_050c19dc1f63d7980a01147272027d22_1_mwpm_03200403.jpeg"},{"uniquekey":"6e60a287047e7cde12e15d7e683eda7b","title":"想在\u201c伦敦眼\u201d上过夜吗？你的机会来了！","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064625217.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170311/20170311064625_6ab7de1d4b9c3bda08d162af3a2e78aa_1_mwpm_03200403.jpeg"},{"uniquekey":"ec8fd52695051cb514825fd422f679cd","title":"法国总统选举在即 马克龙不断巩固领先地位","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064624907.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170311/20170311064624_14e4edf9338b8a4bf71fbe59cf300bfb_1_mwpm_03200403.jpeg"},{"uniquekey":"03bc23556f31283f7dd14ff47c666639","title":"印度：将\u201c庸医\u201d变\u201c祸\u201d为宝 效果喜忧参半","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064624846.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170311/20170311064624_48b9701047ada01ede429e0bfb12c022_1_mwpm_03200403.jpeg"},{"uniquekey":"18a41e3901a940c7da6f2e0a1d8bdfd5","title":"德国再发生斧头袭击事件 7人受伤 嫌疑人被捕","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064624706.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170311/20170311064624_d93e491c42abfb3e48ae502a0bc57389_1_mwpm_03200403.jpeg"},{"uniquekey":"5779c8b2214d3e2aa9deb33658829009","title":"提供窃听资料 阿桑奇欲为科技公司\u201c开小灶\u201d","date":"2017-03-11 06:46","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064624556.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170311/20170311064624_7b078b8d882a88c61918891406e6edb6_1_mwpm_03200403.jpeg"},{"uniquekey":"5c8626dfcb4a2c8b8c86b69fda810a46","title":"笑喷！南非河马泡\u201c无边泳池\u201d享受悠闲假日","date":"2017-03-11 06:46","category":"国际","author_name":"新华网","url":"http://mini.eastday.com/mobile/170311064624253.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170311/20170311064624_faeb2f27c59bca8a9e8d2821713fd824_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170311/20170311064624_91e96034f928385067b93fd3620ae777_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170311/20170311064624_006769a20aca9d2237d282e56d9508cd_3_mwpm_03200403.jpeg"},{"uniquekey":"666ef9f6acc03eec1241d79f7eda1a34","title":"\u201c亲信干政\u201d案后 将有哪些人走上被告席？","date":"2017-03-11 06:45","category":"国际","author_name":"新华社","url":"http://mini.eastday.com/mobile/170311064534983.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170311/20170311064534_623b25d1807acfeea2bd5288e65578d6_1_mwpm_03200403.jpeg"},{"uniquekey":"ecffd9873f4d895cf93ab9f9ebc4b313","title":"设得兰小马喜欢双腿站立跳舞 自带\u201c派对女王\u201d属性","date":"2017-03-11 06:42","category":"国际","author_name":"新华网","url":"http://mini.eastday.com/mobile/170311064252761.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_45fadfc47a0fedc6b3bfd77dd3e35b17_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_0c94c0c7def99cdd229e15ad17a08ad5_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_f63ca54314597c70591f3d53dd367722_3_mwpm_03200403.jpeg"},{"uniquekey":"102bf594cb65889526c464ef137b4465","title":"英国克鲁弗兹狗展持续举行 憨萌名犬亮相比拼","date":"2017-03-11 06:42","category":"国际","author_name":"新华网","url":"http://mini.eastday.com/mobile/170311064252266.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_e4ed26db530812097ae81cd0ab9252c6_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_1943bd1779d0d376a49a03e8b4f99333_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170311/20170311064252_fb424614db9622b67e153e440667b5e8_3_mwpm_03200403.jpeg"},{"uniquekey":"ed7c9f110cd6d6f2ac5aeb1cf15aa720","title":"美国狗狗重76斤险胖死 健身减肥从矮胖墩蜕变成美汪","date":"2017-03-11 06:36","category":"国际","author_name":"新华网","url":"http://mini.eastday.com/mobile/170311063635022.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170311/20170311063635_8d67eea390998efd342f8c3b9859d03e_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170311/20170311063635_32c7727099ac9e432d8bd19b0518b3b2_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170311/20170311063635_3f4b9a48566e70028a5265ae2353cdf0_3_mwpm_03200403.jpeg"},{"uniquekey":"d89ec153d61ca9d014e5b2e12f9a722a","title":"这才是美元下挫元凶？！美高官接连发表惊人言论 美元惨遭两连击","date":"2017-03-11 06:22","category":"国际","author_name":"FX168财经网","url":"http://mini.eastday.com/mobile/170311062213262.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170311/20170311062213_9b13cd0f4bc7ca7873419a97ada5bdf2_1_mwpm_03200403.jpeg"},{"uniquekey":"ae460a9b321f09b00a5abe9e4a123c8a","title":"澳总理回应能源危机问题 承认澳企优势正逐渐丧失","date":"2017-03-11 06:21","category":"国际","author_name":"中新网","url":"http://mini.eastday.com/mobile/170311062100979.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170311/20170311062100_f092d82b9944b91d96224eca438f22b9_1_mwpm_03200403.jpeg"},{"uniquekey":"f9698d767a304eaa711f2c8a4ef40af1","title":"印度狂怼中国却不忘加入上合示好，原来有一事相求不得不低头啊","date":"2017-03-11 06:01","category":"国际","author_name":"第一军情","url":"http://mini.eastday.com/mobile/170311060113084.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170311/20170311060113_2f27d89b2109af4cfc4082d0d62b101a_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20170311/20170311060113_2f27d89b2109af4cfc4082d0d62b101a_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20170311/20170311060113_2f27d89b2109af4cfc4082d0d62b101a_3_mwpm_03200403.jpeg"},{"uniquekey":"c83eccc252381ce718cfb33131269cd8","title":"特朗普正导演能源政策大戏","date":"2017-03-11 05:53","category":"国际","author_name":"人民日报","url":"http://mini.eastday.com/mobile/170311055304779.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170311/20170311055304_e0d633c5a679b85f2174c35b8641bb01_1_mwpm_03200403.jpeg"},{"uniquekey":"41ea6e0c32440359cd319bd668b3f4aa","title":"韩国对中国\u201c出招\u201d？无语！","date":"2017-03-11 05:46","category":"国际","author_name":"人民日报","url":"http://mini.eastday.com/mobile/170311054649208.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170311/20170311054649_6d56b9c37ddd929264b56680b50c0751_1_mwpm_03200403.jpeg"},{"uniquekey":"0dad65648ee9242f76665a7008e17655","title":"朴槿惠被弹劾\u201c挺朴\u201d派民众与警方发生冲突","date":"2017-03-11 05:16","category":"国际","author_name":"光明网-《光明日报》","url":"http://mini.eastday.com/mobile/170311051611964.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170311/20170311051611_ff9bf6444f4dd0ad39c5788c03128e70_1_mwpm_03200403.jpeg"},{"uniquekey":"9eebf1805291538aad59685b38d5e00a","title":"中国驻日大使：中日之间的特点是以民促官","date":"2017-03-11 05:12","category":"国际","author_name":"北京青年报","url":"http://mini.eastday.com/mobile/170311051221399.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170311/20170311051221_8d5014d6179a3510e2d3954de08f0e46_1_mwpm_03200403.jpeg"},{"uniquekey":"222723867c93096c1ed55f477c91e74d","title":"普京：希望见到俄土关系达到新水平","date":"2017-03-11 04:16","category":"国际","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/170311041633507.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170311/20170311041633_c9ce515d997e28804e765cc22c199195_1_mwpm_03200403.jpeg"},{"uniquekey":"2ab1792dbc6568e630dd3d4ab14128b3","title":"首都用了500年的名字都改了，就为摆脱中国，比日本还要自卑！","date":"2017-03-11 04:12","category":"国际","author_name":"历史大观园","url":"http://mini.eastday.com/mobile/170311041249755.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170311/20170311041249_8f5b7d5c17dc6606ab73baaf870ca2bf_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170311/20170311041249_8f5b7d5c17dc6606ab73baaf870ca2bf_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170311/20170311041249_8f5b7d5c17dc6606ab73baaf870ca2bf_3_mwpm_03200403.jpeg"},{"uniquekey":"9df61982c45dedd0fb45ef0bec42f761","title":"芬兰机场地勤人员举行罢工，上百航班受影响被迫取消","date":"2017-03-11 04:07","category":"国际","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/170311040739494.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170311/20170311040739_95c1a645634d3454ce18994520706382_1_mwpm_03200403.jpeg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "stat='" + stat + '\'' +
                    ", data=" + data +
                    '}';
        }

        public static class DataBean {
            /**
             * uniquekey : 9e626a566da849bb771de7ddcfd35591
             * title : 苏富比在纽约启动亚洲艺术周展拍
             * date : 2017-03-11 08:18
             * category : 国际
             * author_name : 新华社
             * url : http://mini.eastday.com/mobile/170311081853185.html
             * thumbnail_pic_s : http://06.imgmini.eastday.com/mobile/20170311/20170311081853_9b401de4f78b6cdbdc177ecc278a6408_1_mwpm_03200403.jpeg
             * thumbnail_pic_s02 : http://06.imgmini.eastday.com/mobile/20170311/20170311081853_7159118301e17072929e05136ef3fb30_2_mwpm_03200403.jpeg
             * thumbnail_pic_s03 : http://06.imgmini.eastday.com/mobile/20170311/20170311081853_8af4fb830c0e68536311f61f70e06bf5_3_mwpm_03200403.jpeg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "uniquekey='" + uniquekey + '\'' +
                        ", title='" + title + '\'' +
                        ", date='" + date + '\'' +
                        ", category='" + category + '\'' +
                        ", author_name='" + author_name + '\'' +
                        ", url='" + url + '\'' +
                        ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                        ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                        ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                        '}';
            }
        }
    }
}

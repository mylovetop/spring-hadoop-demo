//package com.springapp.mvc;
//
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.mock.web.MockServletContext;
//import org.springframework.web.context.request.ServletWebRequest;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Created by zhangjie on 2015/1/20.
// */
//public class MemberControllerTest extends TestAbstract{
//
//
//
//    @Test
//    public void testOpenAccount() throws Exception{
//    	mockMvc.perform((post("/openAccount.html").param("memberId", "00b4e8f7c70f4c84af00e7406a2da3d8")))
// 	   .andExpect(status().isOk()).andDo(print());
//    }
//
//    @Test
//    public void updatePassword() {
//
//        request.setParameter("memberId", "402881e44a4d3da2014a4d4413f50000");
//        request.setParameter("oldPwd", "123456");
//        request.setParameter("newPwd", "123456");
//        request.setParameter("v", "11");
//
//        String memberId = "402881e44a4d3da2014a4d4413f50000";
//        String oldPwd = "123456";
//        String newPwd = "123456";
//
//
//       // ResponseMsg<Map<String, Object>> memberController1 = memberController.updatePassword(memberId, oldPwd, newPwd, request, memberDto);
//
////        System.out.println(memberController1);
////        // assert results
////        Assert.assertEquals(memberController1.getMsg(), "0", memberController1.getStatus().toString());
//    }
//
//    @Test
//    public void updateMember(){
//
//        personalDto.setMemberId("402881e44a4d3da2014a4d4413f50000");
//        personalDto.setTelephone("18888888888");
//        personalDto.setEmail("666666@sina.com");
//        personalDto.setArea("渝北");
//        personalDto.setCity("cq");
//        personalDto.setProvince("重庆");
//        personalDto.setAddress("新牌坊");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateBirthday = new Date();
//		try {
//			dateBirthday = simpleDateFormat.parse("2014-02-06");
//		} catch (ParseException e) {
//
//		}
////        personalDto.setBirthday(dateBirthday);
//        personalDto.setQq("110");
//
////        request.setParameter("memberId", "402881e44a4d3da2014a4d4413f50000");
////        request.setParameter("telephone", "18888888888");
////        request.setParameter("email", "110@sina.com");
////        request.setParameter("area", "渝北");
////        request.setParameter("city", "cq");
////        request.setParameter("province", "重庆");
////        request.setParameter("address", "新牌坊");
////        request.setParameter("birthday", "2014-02-06");
////        request.setParameter("qq", "110");
//
////        ResponseMsg<Map<String, Object>> mapResponseMsg =  memberController.updateMember(request, personalDto);
//
////        Assert.assertEquals(mapResponseMsg.getMsg(), "0", mapResponseMsg.getStatus().toString());
//
//    }
//
//    @Test
//    public void getBackPassword(){
//        request.setParameter("mCheckCode", "5555");
//        request.setParameter("newPwd", "123456");
//
//        memberDto.setRealCd("admin");
//        memberDto.setMobile("13654587957");
//        String mCheckCode = "555";
//        String newPwd = "123456";
//        String module = "";
//
//        //    ResponseMsg<Map<String, Object>> mapResponseMsg = memberController.getBackPassword(mCheckCode, newPwd, module, request, memberDto);
//
//      //  Assert.assertEquals(mapResponseMsg.getMsg(), "0", mapResponseMsg.getStatus().toString());
//    }
//
//    @Test
//    public void realNameAuthentication(){
//        memberDto.setRealNm("zhangjie");
//        memberDto.setMemberId("402881e449507960014950854fba0001");
//        memberDto.setIdCard("510231197410056666");
//
//       //ResponseMsg<Map<String, Object>> msg = memberController.realNameAuthentication(memberDto);
//
//    }
//
//    @Test
//    public void bindCard(){
//
// /*       String memberId = "4028a20e493b69dd01493b69e7ed0000";
//        String bindType ="4";
//        String realNm = "zhangjie";
//        String bankCardNo = "622215814211555577";
//        String idCard = "";
//        String phone = "";
//        String mCheckCode = "";
//        String bankType = "中国银行";
//        String bankName = "";
//        String province = "";
//        String city = "重庆";
//
//        ResponseMsg<BaseResponse> msg = memberController.bindCard(memberId,
//                bindType, realNm, bankCardNo, idCard,
//                phone, mCheckCode, bankType, bankName,
//                province, city);*/
//    }
//
//    @Test
//    public void reg(){
//        memberDto.setMobile("15992370617");
//        memberDto.setPassword("123456");
//        request.setParameter("username", "15992370617");
//        //memberController.reg(request,memberDto);
//    }
//
//    @Test
//    public void login(){
////        username=15992370617&password=123456
//        request.setParameter("username", "15902370617");
//        memberDto.setPassword("123456");
////        /memberController.login(request, memberDto);
//    }
//
//    @Autowired
//    private PersonalService personalService;
//
//    @Test
//    public void findByBirthday(){
//        Date curDate = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
//        List<Personal> personalList = personalService.findByBirthday("01-01");
//        Map<String, String> map;
//        for (Personal p : personalList){
//            map = new HashMap<String, String>();
//            map.put("realCd", p.getMember().getRealCd());
//            System.out.print(p.getMember().getRealCd());
//        }
//    }
//
//
//
//
//}

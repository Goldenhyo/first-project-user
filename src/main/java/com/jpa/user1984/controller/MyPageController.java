package com.jpa.user1984.controller;

import com.jpa.user1984.domain.MemberStatus;
import com.jpa.user1984.dto.*;
import com.jpa.user1984.security.domain.CustomMember;
import com.jpa.user1984.service.InquiryService;
import com.jpa.user1984.service.MemberService;
import com.jpa.user1984.service.MyPageService;
import com.jpa.user1984.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MyPageController {

    private final MyPageService myPageService;
    private final MemberService memberService;
    private final InquiryService inquiryService;
    private final StoreService storeService;
    private final PasswordEncoder memberPasswordEncoder;

    // 회원정보 조회
    @GetMapping("/info")
    public String myPageInfoForm(@AuthenticationPrincipal CustomMember customMember, Model model){
        if(customMember == null){
            return "redirect:/login";
        }
        model.addAttribute("memberInfo", customMember.getMember());
        return "/frontend/myPage/myPageInfo";
    }

    // 회원탈퇴
    @GetMapping("/withdrawal")
    public String withdrawalForm(@AuthenticationPrincipal CustomMember customMember, Model model){
        model.addAttribute("memberInfo", customMember.getMember());
        return "/frontend/myPage/withdrawal";
    }

    // 회원탈퇴 처리
    @PostMapping("/withdrawal")
    public String withdrawalPro(@AuthenticationPrincipal CustomMember customMember, @RequestParam("password") String password, Model model){
        MemberDTO memberDTO = customMember.getMember();
        MemberDTO dbMemberDTO = memberService.findMemberById(memberDTO.getUserNo());
        if(memberPasswordEncoder.matches(password, dbMemberDTO.getUserPassword())){
            memberDTO.setUserStatus(MemberStatus.QUIT);
            memberService.modifyMember(memberDTO);
            return "redirect:/logout";
        }
        model.addAttribute("wrongPassword", true);
        return "/frontend/myPage/withdrawal";
    }

    // 회원정보 수정 전 비밀번호 재입력
    @GetMapping("/modifyCheck")
    public String modifyCheckingForm(Model model){
        model.addAttribute("wrongPassword", false);
        return "/frontend/myPage/modifyCheck";
    }
    // 회원정보 수정 전 비밀번호 재입력 검사
    @PostMapping("/modifyCheck")
    public String modifyCheckingPro(@AuthenticationPrincipal CustomMember customMember, @RequestParam("password") String password, Model model){
        log.info("******* password = {}", password);
        log.info("******* memberPasswordEncoder.encode(password) = {}", memberPasswordEncoder.encode(password));
        MemberDTO memberDTO = customMember.getMember();
        MemberDTO dbMemberDTO = memberService.findMemberById(memberDTO.getUserNo());
        if(memberPasswordEncoder.matches(password, dbMemberDTO.getUserPassword())){
            return "redirect:/myPage/modify";
        }
        model.addAttribute("wrongPassword", true);
        return "/frontend/myPage/modifyCheck";
    }

    // 회원 정보 수정
    @GetMapping("/modify")
    public String modifyForm(@AuthenticationPrincipal CustomMember customMember, Model model){
        model.addAttribute("memberInfo", customMember.getMember());
        return "/frontend/myPage/modify";
    }
    
    // 회원정보 수정처리
    @PostMapping("/modify")
    public String modifyPro(@AuthenticationPrincipal CustomMember customMember, MemberDTO memberDTO){
        memberDTO.setUserNo(customMember.getMember().getUserNo());
        memberDTO.setUserId(customMember.getMember().getUserId());
        memberDTO.setUserStatus(MemberStatus.USER);
        log.info("******* MyPageController /myPage/modify UserName = {}", memberDTO.getUserName());
        log.info("******* customMember No = {}", customMember.getMember().getUserNo());
        memberService.modifyMember(memberDTO);
        return "redirect:/myPage/info";
    }

    // 문의하기 페이지 요청
    @GetMapping("/inquiry/{storeId}")
    public String inquiryForm(@PathVariable("storeId") Long storeId, Model model,
                              @ModelAttribute("inquiryForm") InquiryForm inquiryForm ) {
        model.addAttribute("storeId", storeId);
        String storeTitle = storeService.getOneStore(storeId).getStoreTitle();
        model.addAttribute("storeTitle", storeTitle);
        return "frontend/myPage/inquiryForm";
    }

    // 문의하기 등록 처리 요청
    @PostMapping("/inquiry/{storeId}")
    public String inquiryPro(@AuthenticationPrincipal CustomMember customMember, InquiryForm inquiryForm) {
        log.info("*******************************inquiryForm:{}", inquiryForm);
        Long userNo = customMember.getMember().getUserNo();
        inquiryForm.setUserNo(userNo);
        inquiryService.save(inquiryForm);
        return "redirect:/myPage/inquiryList";
    }

    // 문의내역
    @GetMapping("/inquiryList")
    public String inquiryList(@AuthenticationPrincipal CustomMember customMember, Model model) {
        Long userNo = customMember.getMember().getUserNo();
        List<InquiryDTO> inquiryDTOList = inquiryService.findAllList(userNo);
        log.info("**********MyPageController /inquiryList list:{}", inquiryDTOList);
        model.addAttribute("list", inquiryDTOList);
        return "frontend/myPage/inquiryList";
    }

    // 문의 상세
    @GetMapping("/inquiryDetail/{inquiryId}")
    public String inquiryDetail(@PathVariable Long inquiryId, Model model) {
        InquiryDTO inquiryDTO = inquiryService.findById(inquiryId);
        model.addAttribute("dto", inquiryDTO);
        return "frontend/myPage/inquiryDetail";
    }

    // 나의 책장 조회
    @GetMapping("/myBook")
    public String myBookForm(@AuthenticationPrincipal CustomMember customMember, Model model, PageRequestDTO pageRequestDTO){
        Long userNo = customMember.getMember().getUserNo();
        log.info("********MyPageController /myBook userNo:{}",userNo);
        List<PaymentBookHistoryDTO> bookList = myPageService.findHistoryList(userNo, pageRequestDTO);
        model.addAttribute("bookList", bookList);
        Long count = myPageService.countHistoryList(userNo, pageRequestDTO);
        BookPageResponseDTO bookPageResponseDTO = new BookPageResponseDTO(pageRequestDTO, count, bookList);
        model.addAttribute("pageResponseDTO", bookPageResponseDTO);
        log.info("********MyPageController bookList:{}", bookList);
        return "frontend/myPage/myBook";
    }

    // 나의 서점 조회
    @GetMapping("/myStore")
    public String myStoreForm(@AuthenticationPrincipal CustomMember customMember, Model model, PageRequestDTO pageRequestDTO) {
        Long userNo = customMember.getMember().getUserNo();
        log.info("********MyPageController /myStore userNo:{}",userNo);
        List<PaymentMemDTO> storeList = myPageService.findMembershipList(userNo, pageRequestDTO);
        model.addAttribute("storeList", storeList);
        Long count = myPageService.countMembershipList(userNo, pageRequestDTO);
        MemPageResponseDTO memPageResponseDTO = new MemPageResponseDTO(pageRequestDTO, count, storeList);
        model.addAttribute("pageResponseDTO", memPageResponseDTO);
        return "frontend/myPage/myStore";
    }

    // 도서 구매내역 조회
    @GetMapping("/bookOrderList")
    public String orderList(@AuthenticationPrincipal CustomMember customMember, PageRequestDTO pageRequestDTO, Model model) {
        if (pageRequestDTO.getDateOrder() == null || pageRequestDTO.getDateOrder().equals("desc")) {
            pageRequestDTO.setDateOrder("desc");
        }
        pageRequestDTO.setSize(10);
        Long userNo = customMember.getMember().getUserNo();
        List<PaymentBookHistoryDTO> orderList = myPageService.findHistoryList(userNo, pageRequestDTO);
        model.addAttribute("orderList", orderList);
        Long count = myPageService.countHistoryList(userNo, pageRequestDTO);
        BookPageResponseDTO bookPageResponseDTO = new BookPageResponseDTO(pageRequestDTO, count, orderList);
        model.addAttribute("bookPageResponseDTO", bookPageResponseDTO);
        log.info("----myPageService orderListAjax bookPageResponseDTO : {}", bookPageResponseDTO);
        return "frontend/order/book/list";
    }

    // ajax 도서 구매내역 조회
    @GetMapping("/bookOrderList/ajax")
    public ResponseEntity<BookPageResponseDTO> orderListAjax(@AuthenticationPrincipal CustomMember customMember, PageRequestDTO pageRequestDTO) {
        if (pageRequestDTO.getDateOrder() == null || pageRequestDTO.getDateOrder().equals("desc")) {
            pageRequestDTO.setDateOrder("desc");
        }
        pageRequestDTO.setSize(10);
        log.info("----CmsController orderListAjax pageRequestDTO : {}", pageRequestDTO);
        if (pageRequestDTO.getKeyword() == "") {
            pageRequestDTO.setKeyword(null);
            log.info("***************** CmsController orderListAjax bookPageResponseDTO : {}", pageRequestDTO);
        }
        if (pageRequestDTO.getSearchType() == "") {
            pageRequestDTO.setSearchType(null);
            log.info("***************** CmsController orderListAjax bookPageResponseDTO : {}", pageRequestDTO);
        }
        Long userNo = customMember.getMember().getUserNo();
        List<PaymentBookHistoryDTO> orderList = myPageService.findHistoryList(userNo, pageRequestDTO);
        log.info("&&&&&&&&& CmsController orderListAjax pageRequestDTO 2 : {}", pageRequestDTO);
        Long count = myPageService.countHistoryList(userNo, pageRequestDTO);
        BookPageResponseDTO bookPageResponseDTO = new BookPageResponseDTO(pageRequestDTO, count, orderList);
        log.info("----myPageService orderListAjax bookPageResponseDTO : {}", bookPageResponseDTO);
        return new ResponseEntity<>(bookPageResponseDTO, HttpStatus.OK);
    }

    // 도서 구매내역 상세페이지 조회
    @GetMapping("/bookOrderDetail/{merchantUid}")
    public String bookOrderDetail(@PathVariable("merchantUid") String merchantUid, @RequestParam(required = false) String success, Model model) {
        if ("true".equals(success)) {
            model.addAttribute("paymentSuccess", true);
        } else {
            model.addAttribute("paymentSuccess", false);
        }
        List<PaymentBookHistoryDTO> booksByOrderBookId = myPageService.findBooksByOrderBookId(Long.valueOf(merchantUid));
        model.addAttribute("orderList", booksByOrderBookId);
        PaymentBookHistoryDTO firstDTO = booksByOrderBookId.get(0);
        model.addAttribute("fistDTO", firstDTO);
        return "frontend/order/book/detail";
    }

    // 도서 구매내역 삭제
    @DeleteMapping("/bookOrderDetail/{orderBookId}/delete")
    public String bookOrderDelete(@PathVariable Long orderBookId) {
        myPageService.deleteBookOrder(orderBookId);
        return "redirect:/bookOrderList";
    }

    // 서점 구독내역 상세페이지 조회
    @GetMapping("/membershipOrderDetail/{merchantUid}")
    public String membershipOrderDetail(@PathVariable("merchantUid") String merchantUid,  @RequestParam(required = false) String success, Model model) {
        if ("true".equals(success)) {
            model.addAttribute("paymentSuccess", true);
        } else {
            model.addAttribute("paymentSuccess", false);
        }
        PaymentMemDTO findDTO = myPageService.findMembershipByOrderMembershipId(Long.valueOf(merchantUid));
        model.addAttribute("detail", findDTO);
        return "frontend/order/membership/detail";
    }

    // 서점 구매내역 삭제
    @DeleteMapping("/membershipOrderDetail/{orderMembershipId}/delete")
    public String membershipOrderDelete(@PathVariable Long orderMembershipId) {
        log.info("********* Controller membershipOrderDelete orderMembershipId : {}", orderMembershipId);
        myPageService.deleteMembershipOrder(orderMembershipId);
        return "redirect:/membershipOrderList";
    }

    // 서점 구독내역 조회
    @GetMapping("/membershipOrderList")
    public String membershipOrderList(@AuthenticationPrincipal CustomMember customMember,PageRequestDTO pageRequestDTO, Model model) {
        if (pageRequestDTO.getDateOrder() == null || pageRequestDTO.getDateOrder().equals("desc")) {
            pageRequestDTO.setDateOrder("desc");
        }
        pageRequestDTO.setSize(10);
        Long userNo = customMember.getMember().getUserNo();
        List<PaymentMemDTO> membershipList = myPageService.findMembershipList(userNo, pageRequestDTO);
        model.addAttribute("membershipList", membershipList);
        Long count = myPageService.countMembershipList(userNo, pageRequestDTO);
        MemPageResponseDTO memPageResponseDTO = new MemPageResponseDTO(pageRequestDTO, count, membershipList);
        model.addAttribute("memPageResponseDTO", memPageResponseDTO);
        return "frontend/order/membership/list";
    }

    // ajax 서점 구독내역 조회
    @GetMapping("/membershipOrderList/ajax")
    public ResponseEntity<MemPageResponseDTO> membershipOrderListAjax(@AuthenticationPrincipal CustomMember customMember, PageRequestDTO pageRequestDTO) {
        if (pageRequestDTO.getDateOrder() == null || pageRequestDTO.getDateOrder().equals("desc")) {
            pageRequestDTO.setDateOrder("desc");
        }
        if (pageRequestDTO.getKeyword() == "") {
            pageRequestDTO.setKeyword(null);
        }
        if (pageRequestDTO.getSearchType() == "") {
            pageRequestDTO.setSearchType(null);
        }
        if (pageRequestDTO.getDateOrder() == null || pageRequestDTO.getDateOrder().equals("desc")) {
            pageRequestDTO.setDateOrder("desc");
        }
        pageRequestDTO.setSize(10);
        log.info("----CmsController membershipOrderListAjax pageRequestDTO : {}", pageRequestDTO);
        Long userNo = customMember.getMember().getUserNo();
        List<PaymentMemDTO> membershipList = myPageService.findMembershipList(userNo, pageRequestDTO);
        Long count = myPageService.countMembershipList(userNo, pageRequestDTO);
        MemPageResponseDTO memPageResponseDTO = new MemPageResponseDTO(pageRequestDTO, count, membershipList);
        return new ResponseEntity<>(memPageResponseDTO, HttpStatus.OK);
    }



}

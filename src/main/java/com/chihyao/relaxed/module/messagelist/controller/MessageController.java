package com.chihyao.relaxed.module.messagelist.controller;

import com.chihyao.relaxed.module.messagelist.controller.request.ui.AttachmentVO;
import com.chihyao.relaxed.module.messagelist.controller.request.ui.MessageVO;
import com.chihyao.relaxed.module.messagelist.entity.Attachment;
import com.chihyao.relaxed.module.messagelist.entity.Message;
import com.chihyao.relaxed.module.messagelist.repository.ImageRepository;
import com.chihyao.relaxed.module.messagelist.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping
    public String showMessage() {
        return "page/message_list";
    }

    @GetMapping("/fragment/create")
    public String getCreateFragment(Model model) {
        MessageVO formData = new MessageVO();
        model.addAttribute("formData", formData);
        return "page/message_fragments :: create_form";
    }

    @PostMapping
    public String doCreate(
            HttpServletRequest request,
            @Valid @ModelAttribute("formData") MessageVO formData,
            BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            return "forward:api/messages/upload";
        }
        return "page/message_fragments :: create_form";
    }

    @GetMapping("{messageId}/fragment/attachemts")
    public String getAttachmentsFragment(@PathVariable("messageId") UUID messageId, Model model) {
        Message message = messageRepository.findById(messageId).get();
        List<Attachment> attachments = message.getAttachments();

        List<AttachmentVO> attachmentVOS = attachments.stream().map(attachment -> {
            AttachmentVO attachmentVO = new AttachmentVO();
            attachmentVO.setName(attachment.getName());
            attachmentVO.setSize(attachment.getSize());
            attachmentVO.setLink(MessageFormat.format("api/messages/{0}/images/{1}", messageId, attachment.getId()));
            return attachmentVO;
        }).collect(Collectors.toList());

        model.addAttribute("attachments", attachmentVOS);
        return "page/message_fragments :: list_attachemts";
    }
}

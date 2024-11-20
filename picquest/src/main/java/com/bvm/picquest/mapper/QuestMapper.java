package com.bvm.picquest.mapper;

import com.bvm.picquest.dto.Quest;
import com.bvm.picquest.dto.QuestTransferForm;
import com.bvm.picquest.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

@Mapper
public interface QuestMapper {
    List<QuestTransferForm> findQuestTransferFormByDate(String email, Date date);
    List<Quest> findByDate(Date date);
}

package com.bvm.picquest.mapper;

import com.bvm.picquest.dto.CompleteQuest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompleteQuestMapper {
    int findByEmailAndQuestId(CompleteQuest completeQuestInfo);
    int insert(CompleteQuest completeQuestInfo);
    int update(CompleteQuest completeQuestInfo);
}

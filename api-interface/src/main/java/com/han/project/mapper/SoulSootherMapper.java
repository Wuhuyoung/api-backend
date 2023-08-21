package com.han.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.han.project.domain.SoulSoother;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86183
* @description 针对表【soul_soother(心灵鸡汤)】的数据库操作Mapper
* @createDate 2023-03-21 18:43:45
* @Entity com.han.project.domain.SoulSoother
*/
@Mapper
public interface SoulSootherMapper extends BaseMapper<SoulSoother> {

    String getRandSoulSoother();
}





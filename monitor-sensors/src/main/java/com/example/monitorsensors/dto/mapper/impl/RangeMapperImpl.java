package com.example.monitorsensors.dto.mapper.impl;

import com.example.monitorsensors.dto.RangeDto;
import com.example.monitorsensors.dto.mapper.RangeMapper;
import com.example.monitorsensors.entity.Range;
import org.springframework.stereotype.Service;

@Service
public class RangeMapperImpl implements RangeMapper {

    @Override
    public Range toEntity(RangeDto rangeDto) {
        Range range = new Range();
        updateFromDto(range, rangeDto);
        return range;
    }

    @Override
    public RangeDto toDto(Range range) {
        RangeDto rangeDto = new RangeDto();
        rangeDto.setFrom(range.getFrom());
        rangeDto.setTo(range.getTo());
        return rangeDto;
    }

    @Override
    public Range updateFromDto(Range range, RangeDto rangeDto) {
        if (range == null){
            range = new Range();
        }
        range.setTo(rangeDto.getTo());
        range.setFrom(rangeDto.getFrom());
        return range;
    }
}

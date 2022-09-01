package com.example.monitorsensors.dto.mapper;

import com.example.monitorsensors.dto.RangeDto;
import com.example.monitorsensors.entity.Range;

public interface RangeMapper {

    Range toEntity(RangeDto rangeDto);

    RangeDto toDto(Range range);

    Range updateFromDto(Range range, RangeDto rangeDto);
}

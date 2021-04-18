package com.example.demo.dto;

import com.example.demo.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class UserHasMark {
    private String fulName;
    private List<Integer>  marks;
    private List<Date>  dates;
}

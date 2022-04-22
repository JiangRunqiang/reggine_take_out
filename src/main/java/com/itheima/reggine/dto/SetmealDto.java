package com.itheima.reggine.dto;


import com.itheima.reggine.entity.Setmeal;
import com.itheima.reggine.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}

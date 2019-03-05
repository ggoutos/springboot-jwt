package com.example.demo.utils;

import com.example.demo.domain.Review;
import com.example.demo.model.ReviewModel;

import java.util.ArrayList;
import java.util.List;

public class ModelMapper {

    public static List<ReviewModel> reviewListToReviewModelList(List<Review> list) {
        List<ReviewModel> reviewModelList = new ArrayList<>();
        list.forEach(review -> reviewModelList.add(new ReviewModel(review)));
        return reviewModelList;
    }

}

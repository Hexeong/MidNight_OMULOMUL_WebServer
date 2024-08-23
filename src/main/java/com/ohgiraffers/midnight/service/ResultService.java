package com.ohgiraffers.midnight.service;

import com.ohgiraffers.midnight.entity.Result;
import com.ohgiraffers.midnight.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResultService {
    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<Result> getAllResultsByUserName(String userName) {
        return resultRepository.findResultsByUserName(userName);
    }

    public void registResult(Map<String, Object> result, String username) {
        resultRepository.save(new Result(
            // TODO:: map에서 저장 로직 구현
                (int) result.get("score"),
                (String) result.get("reason"),
                username
        ));
    }

    public Result findResultByResultNo(int resultNo) {
        return resultRepository.findResultByResultNo(resultNo);
    }

    public Result findRecentResult() {
        return resultRepository.findTopByOrderByCreatedAtDesc();
    }
}

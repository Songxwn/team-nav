package com.tuituidan.openhub.controller;

import com.tuituidan.openhub.bean.dto.CardDto;
import com.tuituidan.openhub.bean.dto.SortDto;
import com.tuituidan.openhub.bean.vo.CardVo;
import com.tuituidan.openhub.bean.vo.HomeDataVo;
import com.tuituidan.openhub.consts.Consts;
import com.tuituidan.openhub.service.CardService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * CardController.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2020/10/2
 */
@RestController
@RequestMapping(Consts.API_V1)
public class CardController {

    @Resource
    private CardService cardService;

    /**
     * 首页获取树结构的卡片
     *
     * @param keywords 卡片关键字搜索
     * @return List
     */
    @GetMapping("/card/tree")
    public ResponseEntity<HomeDataVo> tree(String keywords) {
        return ResponseEntity.ok(cardService.tree(keywords));
    }

    /**
     * 卡片维护列表根据分类编号查询
     *
     * @param category category
     * @return List
     */
    @GetMapping("/category/{category}/card")
    public ResponseEntity<List<CardVo>> select(@PathVariable("category") String category) {
        return ResponseEntity.ok(cardService.select(category));
    }

    /**
     * 查询申请列表数量
     *
     * @return List
     */
    @GetMapping("/apply/cards/count")
    public ResponseEntity<Long> countApply() {
        return ResponseEntity.ok(cardService.countApply());
    }

    /**
     * 查询申请列表
     *
     * @return List
     */
    @GetMapping("/apply/cards")
    public ResponseEntity<Page<CardVo>> selectApply(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return ResponseEntity.ok(cardService.selectApply(pageIndex - 1, pageSize));
    }

    /**
     * 通过申请
     *
     * @param id id
     * @return Void
     */
    @PatchMapping("/card/{id}/actions/pass_apply")
    public ResponseEntity<Void> passApply(@PathVariable("id") String[] id) {
        cardService.passApply(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 新增卡片
     *
     * @param cardDto 新增模型数据
     * @return Void
     */
    @PostMapping("/card")
    public ResponseEntity<Void> add(@RequestBody CardDto cardDto) {
        cardService.save(null, cardDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改卡片
     *
     * @param id id
     * @param cardDto 修改数据模型
     * @return Void
     */
    @PatchMapping("/card/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody CardDto cardDto) {
        cardService.save(id, cardDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * 排序
     *
     * @param category 分类id
     * @param sortDto sortDto
     * @return Void
     */
    @PatchMapping("/category/{category}/card/actions/sort")
    public ResponseEntity<Void> changeSort(@PathVariable("category") String category,
            @RequestBody SortDto sortDto) {
        cardService.changeSort(category, sortDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * 根据ID删除一个卡片
     *
     * @param id id
     * @return Void
     */
    @DeleteMapping("/card/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String[] id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

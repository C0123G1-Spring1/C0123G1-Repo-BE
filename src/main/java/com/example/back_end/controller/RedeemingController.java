package com.example.back_end.controller;

import com.example.back_end.dto.open_contract.IOpenContractDTO;
import com.example.back_end.dto.open_contract.OpenContractDTO;
import com.example.back_end.model.Contracts;
import com.example.back_end.service.IRedeemingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/employee/redeem")
@CrossOrigin("*")
public class RedeemingController {
    @Autowired
    private IRedeemingService iRedeemingService;

    /**
     * Created by: HoaTQ
     * Date created: 13/07/2023
     * Function: redeem the pawned object
     * <p>
     * param: contractID
     * void: update contractStatus to closed
     */
    @Transactional
    @PatchMapping ("/pay/{id}")
    public void redeem(@PathVariable("id") Long id, @RequestBody Contracts contracts) {
        iRedeemingService.redeem(id, contracts.getRedeemDate());
    }



    /**
     * Created by: HoaTQ
     * Date created: 13/07/2023
     * Function:  findById()
     * <p>
     * param: contractId (from PathVariable)
     * return: the list of contract with the contractStatus is open and match the contractId
     */
    @GetMapping("/chose/{id}")
    public ResponseEntity<Contracts> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<Contracts>(iRedeemingService.findOpenContract(id), HttpStatus.OK);
    }


    /**
     * Created by: HoaTQ
     * Date created: 13/07/2023
     * Function:  getOpenContractList(page)
     * <p>
     * param: page (the page number that the user want to view)
     * return: the list of contract with the contractStatus is open
     * issue: in progress for searching how to get list from Spring Data JPA DTO Projection
     * I have covered my issue.
     */
    @Transactional
    @GetMapping("/chooseContract")
    public ResponseEntity<Page<OpenContractDTO>> getOpenContractList(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Order.desc("create_time")));
        Page<OpenContractDTO> contractsPage = iRedeemingService.findPageConTract(pageable);

        if (contractsPage == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contractsPage, HttpStatus.OK);
    }


    /**
     * Created by: HoaTQ
     * Date created: 13/07/2023
     * Function: getOpenContractSearchList()
     * <p>
     * param: page,contractCode,customerName,productName,startDate (from RequestParam on URL )
     * return: the list of contract with the contractStatus is open and match to those params
     */
    @Transactional
    @GetMapping("/search")
    public ResponseEntity<Page<OpenContractDTO>> getOpenContractSearchList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                     @RequestParam(value = "contractCode") String contractCode,
                                                                     @RequestParam(value = "customerName") String customerName,
                                                                     @RequestParam(value = "productName") String productName,
                                                                     @RequestParam(value = "startDate") String startDate) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Order.desc("create_time")));
        Page<OpenContractDTO> contractsPage = iRedeemingService.searchPageConTract(pageable, '%' + contractCode + '%', '%' + customerName + '%', '%' + productName + '%','%' + startDate + '%');

        if (contractsPage == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contractsPage, HttpStatus.OK);
    }

}

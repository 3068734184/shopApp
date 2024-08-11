package com.example.shopweb.controller;

import com.example.shopmodel.model.AddressBook;
import com.example.shopmodel.vo.AddressBookVO;
import com.example.shopweb.service.AddressBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressBook")
public class AddressBookController {
    private AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @PostMapping("/addAddress")
    public int insertAddressBook(AddressBook addressBook) {
        return addressBookService.insertAddressBook(addressBook);
    }

    @GetMapping("/selectById")
    public AddressBookVO selectAddressBookById(Integer id) {
        return addressBookService.selectAddressBookById(id);
    }

    @GetMapping("/selectAddressBooksByUserId")
    public List<AddressBookVO> selectAddressBookByUserId(Integer userId) {
        return addressBookService.selectAddressBooksByUserId(userId);
    }

    @DeleteMapping("/deleteById")
    public int deleteAddressBookById(Integer id) {
        return addressBookService.deleteAddressBooksById(id);
    }
}

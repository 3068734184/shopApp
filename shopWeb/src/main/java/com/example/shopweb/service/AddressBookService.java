package com.example.shopweb.service;

import com.example.shopmodel.model.AddressBook;
import com.example.shopmodel.vo.AddressBookVO;
import com.example.shopweb.mapper.AddressBookMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {
    private AddressBookMapper addressBookMapper;

    public AddressBookService(AddressBookMapper addressBookMapper) {
        this.addressBookMapper = addressBookMapper;
    }

    public int insertAddressBook(AddressBook addressBook) {
        return addressBookMapper.insertAddressBook(addressBook);
    }

    public AddressBookVO selectAddressBookById(Integer id) {
        AddressBook addressBook = addressBookMapper.selectAddressBookById(id);
        AddressBookVO addressBookVO = new AddressBookVO();
        if (addressBook == null) {
            return null;
        }
        BeanUtils.copyProperties(addressBook, addressBookVO);
        String format = String.format("%s %s %s %s", addressBook.getProvince_name(), addressBook.getCity_name(),
                addressBook.getDistrict_name(),addressBook.getDetail());
        addressBookVO.setAddress(format);
        return addressBookVO;
    }

    public List<AddressBookVO> selectAddressBooksByUserId(Integer userId) {
        List<AddressBook> addressBooks = addressBookMapper.selectAddressBooksByUserId(userId);
        ArrayList<AddressBookVO> addressBookVOS = new ArrayList<>();
        if (addressBooks == null || addressBooks.isEmpty()) {
            return addressBookVOS;
        }
        for (AddressBook addressBook : addressBooks) {
            AddressBookVO addressBookVO = new AddressBookVO();
            BeanUtils.copyProperties(addressBook, addressBookVO);
            String format = String.format("%s %s %s %s", addressBook.getProvince_name(), addressBook.getCity_name(),
                    addressBook.getDistrict_name(), addressBook.getDetail());
            addressBookVO.setAddress(format);
            addressBookVOS.add(addressBookVO);
        }
        return addressBookVOS;
    }

    public int deleteAddressBooksById(Integer id) {
        return addressBookMapper.deleteAddressBook(id);
    }
}

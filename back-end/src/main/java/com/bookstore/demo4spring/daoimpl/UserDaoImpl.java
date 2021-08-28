package com.bookstore.demo4spring.daoimpl;

import com.bookstore.demo4spring.dao.UserDao;
import com.bookstore.demo4spring.entity.*;
import com.bookstore.demo4spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAvatarRepository userAvatarRepository;

    @Override
    public User getUserById(int user_id){
        User u = userRepository.getUser(user_id);
        Optional<UserAvatar> avatar = userAvatarRepository.findById(u.getUserId());

        if(avatar.isPresent()) u.setAvatar(avatar.get());
        else u.setAvatar(null);

        userRepository.save(u);
        return u;
    }

    @Override
    public UserAuth checkUser(String username, String password){
        return userAuthRepository.checkUser(username,password);
    }

    @Override
    public User updateBalance(int user_id, double bills){
        User u = userRepository.getUser(user_id);
        double oldBalance = u.getBalance();
        double newBalance = oldBalance+bills;
        u.setBalance(newBalance);
        userRepository.save(u);
        return u;
    }

    @Override
    public double getBalance(int user_id){
        return userRepository.getBalance(user_id);
    }

    @Override
    public UserAuth getUserByName(String username) { return userAuthRepository.getUserByName(username);}

    @Override
    public User addUser(String username, String password, String nickname, String name, String email, String tel, String address){
        User u = new User();
        u.setNickname(nickname);
        u.setName(name);
        u.setEmail(email);
        u.setTel(tel);
        u.setAddress(address);
        userRepository.save(u);
        UserAvatar uav = new UserAvatar(u.getUserId(), "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAO7UlEQVR4Xu2dWawnRRXGf/MgKCAmGjKCgLKJYRGjhh1ZBdkFWTSiRgFRwyIIib6I+qIJCLJEQUATQNlkkUUE2VQEJGpElqCIgCBIiJooDIoPmk/6yp1h7r1d1V3VVd1fJZ07k1Sdc+o7/Uv/q6u6ahEuVsAKzKnAImtjBazA3AoYEN8dVmAeBQyIbw8rYEB8D1iBOAX8BInTza0mooABmUii3c04BQxInG5uNREFDMhEEu1uxilgQOJ0c6uJKGBAJpJodzNOAQMSp5tbTUSBlICsBGwLLG6uVSaiqbuZR4Fngaeb63ZgSQq3KQA5DNgd2D9FwLZpBeZQ4ArgeuDcPhXqE5B9gKOBnfsM0LasQKACNwOnA1cHtltu9b4A+QJwYh8B2YYV6EmBLwK6LzuVPgAxHJ1S4MYJFegMSVdADgQuTdhBm7YCXRU4CLgs1kgXQDTm+H6sY7ezAhkV2Dd2TNIFkJs8IM+YYrvqooAG7rvEGIgFRK9yz4lx6DZWYCAFDo95BRwLyA+auY6B+mq3ViBYAc2R7BHaKgYQzYj/I9SR61uBAhR4NaAZ+NYlBpBdgRtae3BFK1COArsBN4aEEwPIh4DzQ5y4rhUoRIEPAxeExBIDyPHASSFOgB2B2wLbuLoVmE+BHYBbAyU6ATg5pE0MIDEz5zF+QvrhutNU4D+B3Q6eWY+5cQ1IYFZcPZkCBiSZtDY8BgUMyBiy6D4kU8CAJJPWhseggAEZQxbdh2QKGJBk0trwGBQwIGPIovuQTAEDkkxaGx6DAgZkDFl0H5IpYECSSWvDY1DAgIwhi/P04VXAGsAbmr/6t8qTzfWn5u/zI9chtnsGJFa5Atut1Xz2uSewYQPEa1vG+dcGlN8C1wH63Pnxlm3HXM2AVJzdFYB3AdsB7wE277kvdwM/BH4K/AR4oWf7NZgzIDVkaZkYNwKOArSTxuqZ4n+q2WHmDOCBTD5LcGNASshCyxg0jjiyuYbaqFufk57ZXBq/jL0YkAoyLBhmwBAkJRTBMQNK0DfYJQQfEIMBCRBriKqfaODYeAjnLXze34ByVou6NVYxIAVn7TzgYwXHNzu0bwGHVhJrSJgGJEStTHVXA64Cts7kry83dwDvBZ7py2ABdgxIAUmYHcKWzTxE2/mLwsJH8ymah7mrtMAi4zEgkcKlaHZI6JYxKYLoyaa2brqwJ1tDmjEgQ6o/y3fMRhWFhD5nGME7fBTYIQNSQFK0p9ctBcSRIoSdIvaWShFHrE0DEqtcT+02AH7Xk61SzbwZeKjU4BaIy4AMmLhVAc0jrDlgDDlcPwFoHufvOZz17MOA9CxoiLmLgYNDGlRc9xLg/RXGb0AGStppzZHWA7kfxK2OTj5mEM/xTg1IvHbRLbV85BvRretu+EmgpmUpBiTz/aaFh5pEK3VtVWo5NObSZGgtCxwNSOo7Yhn7nwW+nNlnae4+B3yltKDmiMeAZEyUlqr/vPk+PKPb4lxpqfwWQA3fkxiQjLePnhx6gri8+ATRk6T0YkAyZUifyerpMdSXgJm62dqNxiB6ipT++a4BaZ3SbhX11kpvr1xeUkBvs/RWq+RiQDJkZ0XgMWBxBl81uXgaeCPwr4KDNiAZkrMXcE0GPzW62Bu4tuDADUiG5JwCHJvBT40uTgWOKzhwA5IhOb8GNsvgp0YX9wBvKzhwA5I4OesAf0jso3bz6wKPFNoJA5I4MUdUtvYosRzLNa+3e2cP4biFTwPSQqQuVa4A9utiYAJtrwT2L7SfBiRxYu4FNknso3bz9wGbFtoJA5I4MX8Bat3CJ7E0/zevrYJel8tZoB8DEihYSHUdXrMkpMGE664ElHiIjwFJeFOuB/w+of0xmV4feLjADhmQhEnR4TY/Tmh/TKa3bw7pKa1PBiRhRrRJwUUJ7Y/J9AcAbWJRWjEgCTOiJRRfTWh/TKY/A2hJTmnFgCTMiAFpL64Baa8VMfvULgqwn6uqf2K1V9o/sdprNRpAPEhvn3QP0ttrNRpA/Jq3fdL9mre9VqMBxBOF7ZPuicL2Wo0GEHXZS00WTryXmiys0VI1xjJIV6e8WHHh5Hux4sIajRaQ7wHvC+z/1KpfDhxQaKc9D5I4MR8FdESyy9wK6KjrbxcqkAFJnJi1gD8m9lG7+bWBxwvthAHJkBjtqLh5Bj81uri72WGx1NgNSIbM6LTXz2fwU6OLLwEnFhy4AcmQnF2AH2XwU6OLdwM3FRy4AcmQnBWAR4HVM/iqycVTwJuAFwoO2oBkSo43r3650N68GohZZTumicKZ28LHHywNiI8/aPQwIC/dGD5A5yUtfICOAXnZbwofwfaiJD6Cbdat4SfI0pz4EM8Xj17zIZ5+gix3+O9joH0M9FI3hp8gL+dEmzXrrdYUi45c09urWopf8w6UqfMALdKbUtGizUMr67ABGTBhPwO2HtB/Ttd3ANvkdNiTLwPSk5AxZlYDHpzA5tb6YvAtwDMxIg3cxoAMnIAtgTsHjiG1+62Au1I7SWTfgCQSNsTsIcAFIQ0qqvsh4MKK4l02VANSSPJiltcUEvqcYWiZv/pVczEgBWVvR+CWguLpEspOwK1dDBTS1oAUkoiZMDZoIFmzsLjahvMEIDgeatug8HoGpMAErQp8Ezi4wNjmC+kS4OPA3yuLe75wDUjByTwNOLrg+GaHdjpwTCWxhoRpQELUGqCulqUcCWw8gO82Lu8Hzqxs+Uibfs3UMSAhag1UVwscBYkuLZkvoWjJusDQpY+fxloMSEWZFRwzoAiaIYpgmAFDkIy9GJAKM6zPd48C9gMWZ4r/aeBK4AzggUw+S3BjQErIQmQMKwLaNkevVXVtFmlnrmb3NK+cNTejbYv+1bP9GswZkBqy1DLGdYBdgd0AzaesEbAQUgsKn2zmL24AbgQeael3zNUMyJizC+gQH4Gi8Yv+6lIRDLo0jtDf50euQ2z3DEiscm43CQUMyCTS7E7GKmBAYpWroN0rAZ39N/tauYn7OWDJMtc/K+hT7hANSG7FE/jT/rZ69avZ9tl/Z2Bo61LQ6BWuZsdn/9W+wlMtBqTCzOsrxO2aS2exvyZxH/7WvO69GdBZJ79K7K8k8wakpGzMEYte3+4FbN9s8jD0LvFa0q4NJ3RdO/LXwQakUEBeD+zZgKG/ryg0zn8D1zWg6O+fC40zNiwDEqtconZ6UnygAUPfhdRU9B2InigXNX9rin2uWA1IIVkUGPrYaO9C4ukaxjXNR18CpuZiQAbO3tjAWFbO2kExIAMBsgNwLLDPQP5zu70aOBW4Lbfjjv4MSEcBQ5uvBxwHfCq04Ujqfx04BXi4kv4YkEyJ0tJ0PTEEh7YcnXLRFqSCRE+U0pfQG5AMd6qWoOv4tbdn8FWTC0046jAdLa0vtRiQxJk5HjgpsY/azZ8AnFxoJwxIosToU1iBob1pXRZWQHsTCxR92ltSMSAJsqGfVILjrQlsj9nkbxpISvrJZUB6vuM+WPlu5j3LEWVOb/hKOaLOgESlcPmNDm9mj3s0OVlTpZyjbkB6ugU9GO9JyFlmvgvoiTxkMSA9qH++B+M9qLh8E9cDeySzvrBhA7KwRvPW0IBSe1O5pFNAG9btn878vJYNSAfhLwMO6NDeTdsrMNRpVQakfY6WqqmlElo64pJPAe10f3Y+d//zZEAiBP90s44ooqmbdFTgI4DGfLmKAQlUWhtGXxHYxtX7VUDjEY1LchQDEqCylqpr31r9dRlOAS2V1/7DOZbMG5CAPOvJoSeIy/AK5HqzZUBa5nqM55i37Hqx1XK82TIgLdLvcUcLkQaqkno8YkAWSKzOK9d31B53DETAAm41DtH3/drMLkUxIAuo6vmOFLddvzb16a4+ZU5RDMg8qm4F3JFCddvsXYGtgTt7t+qJwnklvRQ4MIHoNtm/Alr2c1D/Zj2TPpemAkOAuNSjgAARKH0W/8SaQ039tNJPLJd6FNBPLP3U6rMYkOWoqT1ycy+K6zOpU7Z1RM9fdRqQ5dxNvwDeMeW7rOK+/xJ4Z4/xG5BlxPTTo8e7ayBTfT5FDMgySfTTY6C7uke3fT5FDMisxPjp0eNdOrCpvp4iBmRWInVA5eYDJ9bu+1HgbmCLHkwZkEbEbYDbexDUJspRYNvmoNEuERmQRj3tvv7ZLkq6bXEK9LH5nAFp0qp9YTctLsUOqIsC9/awP7IBAXYBftQlE25brALas+ymDtEZEOBrwDEdRHTTchU4DdAuNLHFgAD3ARvHKuh2RSvwQMfcTh6QDYEHi06xg+uqwCbA/ZFGJg+IToDKuRFZZJ7crIMCXc4bmTwgZwGadXUZrwLfAQ6J7N7kAXkIWD9SPDerQ4FHgXUiQ500IGsDj0UK52Z1KbAu8EhEyJMGxJ/VRtwxlTaJ/Rx30oCcCGjHRJfxKxC7C+OkAfGuJeMHY6aHsbueTBoQTxBOBxDNg2g+JLRMGpDQzoeK6/plKbAoIpzQeyT4p1xMUDE7qYf62ajD7GqEzm5SgAJaTqSlJyFlsoB4BW/IbTKOurtGrNqeLCD7AN8fR97di5YK6BiLq1rWnalWJCDHAycFdmTH5piCts0OBi5uW9n1RqGAlpto2UnbomMVbm1bual3AnBySJvQsYFsewFhiMKuW5ICHwYuCAkoBhD9VtRhmS5WoDYFdLjojSFBxwCyEvBciBPXtQKFKLAysCQklhhAZP9yQOfPuViBWhTQKcjvCw02FpDDgHNCnbm+FRhQgcOBc0P9xwIiP9qNYudQh65vBQZQ4OZmd5xg110A8VxFsNxuMJAC+wJXx/juAoj8xSw7iYnTbaxArALB669mO+oKiCGJTZvb5VCgExwKsA9AZMdf/+VIt32EKBD7leJSPvoCREY1JjnaA/eQHLpuAgU0ID89dsyxbDx9AjJjW6+ANUeye4LO26QVmEuB6wHNdQS/yp1P0hSAzPhbpTn2dzGgS/93sQJ9KfAs8HRz6Vhw/b/3khKQ3oO1QSuQWwEDkltx+6tKAQNSVbocbG4FDEhuxe2vKgUMSFXpcrC5FTAguRW3v6oUMCBVpcvB5lbAgORW3P6qUsCAVJUuB5tbAQOSW3H7q0oBA1JVuhxsbgUMSG7F7a8qBf4L0p1g9o/Ko4MAAAAASUVORK5CYII=");
        userAvatarRepository.save(uav);

        u.setAvatar(uav);

        UserAuth ua = new UserAuth();
        ua.setUserId(u.getUserId());
        ua.setUser(u);
        ua.setUsername(username);
        ua.setPassword(password);
        ua.setUserType(1);
        userAuthRepository.save(ua);
        return u;
    }

    @Override
    public void updateUser(User user){
        userRepository.save(user);
    }

    @Override
    public User updateUser(int user_id, String avatar, String nickname, String email, String tel, String address, String password){
        User u = userRepository.getUser(user_id);
        UserAuth ua = userAuthRepository.getOne(user_id);
        u.setEmail(email);
        u.setTel(tel);
        u.setAddress(address);
        u.setNickname(nickname);
        ua.setPassword(password);

        Optional<UserAvatar> a = userAvatarRepository.findById(user_id);

        if(a.isPresent()){
            System.out.println(a.get().getId());
            a.get().setAvatar(avatar);
            u.setAvatar(a.get());
            userAvatarRepository.save(a.get());
        } else {
            u.setAvatar(null);
            System.out.println("avatar is null");
        }
        userRepository.save(u);
        userAuthRepository.save(ua);
        return u;
    }

    @Override
    public List<UserAuth> getAllUsers(){
        List<UserAuth> ul = userAuthRepository.findAll();

        for(UserAuth ua : ul){
            User u = ua.getUser();
            Optional<UserAvatar> avatar = userAvatarRepository.findById(u.getUserId());

            if(avatar.isPresent()) u.setAvatar(avatar.get());
            else u.setAvatar(null);

            userRepository.save(u);
        }
        return ul;
    }

    @Override
    public UserAuth blockUser(int user_id){
        UserAuth u = userAuthRepository.getOne(user_id);
        u.setUserType(2);
        userAuthRepository.save(u);
        return u;
    }

    @Override
    public UserAuth unBlockUser(int user_id){
        UserAuth u = userAuthRepository.getOne(user_id);
        u.setUserType(1);
        userAuthRepository.save(u);
        return u;
    }
}

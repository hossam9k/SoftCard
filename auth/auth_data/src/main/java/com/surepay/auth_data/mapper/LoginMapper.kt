package com.surepay.auth_data.mapper

import com.surepay.auth_data.remote.entity.LoginData
import com.surepay.auth_domain.login_model.Login


fun LoginData.toLoginDomain(): Login{
    return Login(
        token = token
    )
}
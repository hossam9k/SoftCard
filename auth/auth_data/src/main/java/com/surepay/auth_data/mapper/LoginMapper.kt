package com.surepay.auth_data.mapper

import com.surepay.auth_data.remote.entity.LoginData
import com.surepay.auth_domain.model.Login


fun LoginData.toLoginDomain(): Login{
    return Login(
        completed = completed,
        id = id,
        title = title,
        userId = userId
    )
}
package com.surepay.auth_domain.utils

import com.google.common.truth.Truth
import com.surepay.auth_domain.use_case.utils.isValidEmail
import org.junit.Test

class EmailRegex {

    @Test
    fun `is email valid format and fail`(){
        Truth.assertThat(isValidEmail("a@a.")).isFalse()
    }

    @Test
    fun `is email valid format then passed`(){
        Truth.assertThat(isValidEmail("a@a.com")).isTrue()
    }
}
package com.example.coivd_19mvvm

import org.junit.Test

/**
 * Unit test run after removing NoInternet dead class
 */

class GlobalFragmentTest{

    @Test
    fun `check if NoInternet connection return nonnull`(){
        val result = GlobalFragment().fetchGlobalData()
        assertThat(result).isNotNull()
    }

}
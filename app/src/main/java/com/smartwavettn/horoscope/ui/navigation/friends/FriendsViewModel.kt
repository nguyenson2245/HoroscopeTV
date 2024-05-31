package com.smartwavettn.horoscope.ui.navigation.friends

import com.smartwavettn.horoscope.base.BaseViewModel
import com.smartwavettn.horoscope.model.PersonalInformation

class FriendsViewModel : BaseViewModel() {

    fun getListPersonaLiveData() = repository.getListLiveData()

    suspend fun deletePersonal(personalInformation: PersonalInformation) = repository.deletePersonal(personalInformation)

    suspend fun updatePersonalI(personalInformation: PersonalInformation)=repository.updatePersonalInformation(personalInformation)

}
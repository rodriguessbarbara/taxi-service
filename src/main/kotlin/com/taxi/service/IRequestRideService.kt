package com.taxi.service

import com.taxi.model.Ride

fun interface IRequestRideService {
  fun execute(input: Ride) : String
}
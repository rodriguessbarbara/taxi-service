package com.taxi.service

import com.taxi.model.Ride

fun interface IGetRideService {
  fun execute(rideId: String) : Ride
}
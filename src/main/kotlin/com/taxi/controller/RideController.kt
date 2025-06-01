package com.taxi.controller

import com.taxi.model.Ride
import com.taxi.service.IGetRideService
import com.taxi.service.IRequestRideService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ride")
class RideController(
  private val requestRideService: IRequestRideService,
  private val getRideService: IGetRideService
) {
  
  @PostMapping("/request")
  fun requestRide(@RequestBody input: Ride) : String {
    return try {
      requestRideService.execute(input)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error requesting ride")
    }.toString()
  }
  
  @GetMapping("/get/{rideId}")
  fun getRide(@PathVariable rideId: String) : String {
    return try {
      getRideService.execute(rideId)
    } catch (e: Exception) {
      ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ride not found")
    }.toString()
  }
}
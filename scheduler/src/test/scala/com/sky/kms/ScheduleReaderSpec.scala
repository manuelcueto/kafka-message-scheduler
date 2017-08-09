package com.sky.kms

import java.util.UUID

import com.sky.kms.domain._
import com.sky.kms.streams.ScheduleReader
import common.AkkaStreamBaseSpec
import common.TestDataUtils._

class ScheduleReaderSpec extends AkkaStreamBaseSpec {

  "toSchedulingMessage" should {

    "generate a CreateOrUpdate message if there is a schedule" in {
      val (scheduleId, schedule) = (UUID.randomUUID().toString, random[Schedule])
      ScheduleReader.toSchedulingMessage(Right((scheduleId, Some(schedule)))) shouldBe
        Right(SchedulingActor.CreateOrUpdate(scheduleId, schedule))
    }

    "generate a Cancel message if there is no schedule" in {
      val scheduleId = UUID.randomUUID().toString
      ScheduleReader.toSchedulingMessage(Right((scheduleId, None))) shouldBe
        Right(SchedulingActor.Cancel(scheduleId))
    }
  }

}

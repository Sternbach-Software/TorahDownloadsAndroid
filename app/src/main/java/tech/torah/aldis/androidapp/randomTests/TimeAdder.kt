package tech.torah.aldis.androidapp.randomTests

class TimeAdder {
    /**
     * Converts seconds ([this]) to hours, minutes, seconds format
     * @receiver seconds to convert
     * @return a [Triple] of final hour, minute, second
     * @sample (578).toHrMinSec() = (0, 9, 38)*/
    fun Int.toHrMinSec(): Triple<Int, Int, Int> {
        var hour = 0
        var minute = 0
        var second = this
        minute += (second / 60)
        hour += (minute / 60)
        second %= 60
        minute %= 60
        return Triple(hour, minute, second)
    }
}
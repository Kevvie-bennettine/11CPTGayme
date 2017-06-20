package levels;

/**
 * returns a byte representing allowed direction out of a room,
 * directions are represented by the 4 least significant bytes
 * 0b_abcd, where a = north, b = east, c = south, d = west
 */

public interface AllowedDirectionHandler
{
	public byte handle();
}

# Unsafe World Random Access Detector

This is a mod that detects unsafe off-thread world random access,
helping to find causes of "Accessing LegacyRandomSource from multiple threads" crash.

This is useful for finding the cause of mysterious crashes with `Accessing LegacyRandomSource from multiple threads`.  
This is **not** a fix for the issue, but it will help you find the cause of the issue.

Note: If you are already using C2ME, this mod is not needed (but won't crash if used together anyway).

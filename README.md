xkcd-concurrent
===============

This is a small set of Java files to demonstrate some of the concurrency 
features of more recent JREs.

It was inspired by the xkcd hashing challenge, which has now passed. It is not
technically suitable for it, as the JDK does not have a native Whirlpool 
implementation, and I couldn't be bothered to find a library or write one.

Installing and Running
======================

  - `$> and dist`
  - `$> java -jar ./dist/lib/xkcd-concurrent.jar <somenum>`

Where <somenum> is how many trials each thread will run.

Tests
=====

There are no tests, this was a one-off program. Bugs are almost guaranteed.



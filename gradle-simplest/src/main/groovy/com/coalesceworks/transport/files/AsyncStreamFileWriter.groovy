/**
 * 
 */
package com.coalesceworks.transport.files

import io.vertx.groovy.core.Vertx;
import io.vertx.groovy.core.buffer.Buffer;

/**
 * @author Sandeep Kotha
 *
 */
class AsyncStreamFileWriter {

	static main(args) {
		def vertx = Vertx.vertx()
		vertx.fileSystem().open("target/classes/hello.txt", [:], { result ->
		  if (result.succeeded()) {
			def file = result.result()
			def buff = Buffer.buffer("foo")
			for (def i = 0;i < 5;i++) {
			  file.write(buff, buff.length() * i, { ar ->
				if (ar.succeeded()) {
				  println("Written ok!")
				  // etc
				} else {
				  System.err.println("Failed to write: ${ar.cause()}")
				}
			  })
			}
		  } else {
			System.err.println("Cannot open file ${result.cause()}")
		  }
		})
	}

}

const fs = require('fs');
const http = require('http');
const server = http.createServer();
server.listen(3000, () => { console.log("server is listening port 3000..."); });


// issue: read file asynchronously and write file synchronously
fs.readFile('largeFile.txt', 'utf8', (err, data) => {   // suppose largeFile.txt has millions of lines
    fs.writeFile('largeFile2.txt', data);
});
/*
Workaround steps:
    1. source file: largeFile.txt
        2. Read Buffer: store the data in buffer
            3. readFile: read the file asynchronously and store the data in buffer
                4. writeFile: Once the reading is completed, then start writing the data to the buffer writer.
                    5. Write Buffer: write the data to the file.
                        6. destination file: largeFile2.txt

issue: 
    - Here, we are waiting for the reading to complete and then start writing the data to the file.
    - This is not the best way to do this, because we are blocking the execution of the program until the reading is completed.
-------
Solution: Streams

Streams:
    - Streams are objects that let you read data from a source or write data to a destination in continuous fashion.
    - With Streams, we can process data piece by piece instead of reading or write the data at once.
    - Streams are useful when working with large files or network data.
    - Streams are instances of EventEmitter class.
Workaround steps:
    1. source file: largeFile.txt
    2. Read Stream: read the file using stream (readable stream) and store the data in buffer
    3. Read Buffer: store piece of data in buffer
    4. readFile: call the handler chunk to read the data from the buffer
    5. writeFile: call the handler chunk to write the data to the buffer
    6. Write Buffer: send the chunk of data to the buffer writer
    7. Write Stream: write the chunk of data to the file
    8. destination file: largeFile2.txt

Advantages of Streams:
    - Memory efficient: Streams let you read data piece by piece, so you don't need to load the entire data into memory.
    - Time efficient: Streams let you start processing the data while reading or writing is still in progress.

Types of Streams:
    - Readable: 
        - read or consume data chunk by chunk.
        - fs.createReadStream()
        - Example: Read data from a file, network request, etc.
        - Events:
            - data: Emitted when the stream is passing the data.
            - end: Emitted when there is no more data to read.
            - error: Emitted when an error occurs.
            - close: Emitted when the stream is closed.
        - Methods:
            - pipe(): Used to read data from a readable stream and write it to a writable stream.
            - unpipe(): Used to stop the data flow from a readable stream to a writable stream.
            - read(): Used to read the data from the stream.
            - resume(): Used to resume the stream after a pause.
            - pause(): Used to pause the stream.
            - destroy(): Used to destroy

    - Writable: 
        - write or produce data chunk by chunk.
        - fs.createWriteStream()
        - Example: Write data to a file, network response, etc.
        - Events:
            - drain: Emitted when the stream is ready to write more data.
            - finish: Emitted when the stream is finished writing data.
            - error: Emitted when an error occurs.
            - close: Emitted when the stream is closed.
        - Methods:
            - write(): Used to write data to the stream.
            - end(): Used to end the stream.
            - cork(): Used to buffer data.
            - uncork(): Used to flush the buffer.
            - destroy(): Used to destroy the stream.

    - Duplex: 
        - Used for reading and writing data at the same time.
        - Example: TCP socket.
        - fs.createDuplexStream()
        - Events:
            - data: Emitted when the stream is passing the data.
            - end: Emitted when there is no more data to read.
            - error: Emitted when an error occurs.
            - close: Emitted when the stream is closed.
        - Methods:
            - pipe(): Used to read data from a readable stream and write it to a writable stream.
            - unpipe(): Used to stop the data flow from a readable stream to a writable stream.
            - read(): Used to read the data from the stream.
            - write(): Used to write data to the stream.
            - end(): Used to end the stream.
            - cork(): Used to buffer data.
            - uncork(): Used to flush the buffer.
            - destroy(): Used to destroy the stream.
    - Transform: 
        - A type of duplex stream which can also modify or transform the data as it is read or written.
        - fs.createTransformStream()
        - Example: Compressing data, converting data from one format to another.
        - Events & Methods: Same as Duplex stream.
 */


// Solution 2: Using readable and writable streams
server.on('request', (req, res) => {   // listen to request event
    let readStream = fs.createReadStream('largeFile.txt', 'utf8');

    readStream.on('data', (chunk) => {  // listen to data event
        // let writeStream = fs.createWriteStream('largeFile2.txt', { flags: 'a' }); // {flags: 'a'} append the data
        // writeStream.write(chunk);   // write the data to the file chunk by chunk

        res.write(chunk);   // return the response chunk by chunk to the client
    });
    readStream.on('end', () => {    // listen to end event
        res.end('File copied successfully.');
    });
    readStream.on('error', (err) => {   // listen to error event
        console.log(err);
        res.end('Error occurred.', err.message);
    });
});
/*
- Here, we are reading the file using a readable stream and writing the data to the response using a writable stream.
- This way, we are not blocking the execution of the program.

Issue in Solution 2: Backpressure
What is Backpressure:
    - Backpressure is a mechanism used in streams to prevent the fast producer from overwhelming the slow consumer.
    - Example: If the producer is reading data faster than the consumer can process it, backpressure is applied to slow down the producer.
      - Reading data at 4mb/s and writing data at 1mb/s.
    - To solve this issue, we can use the pipe() method to read data from a readable stream and write it to a writable stream.
*/
// Solution 3: Using pipe() method
server.on('request', (req, res) => {   // listen to request event
    let readStream = fs.createReadStream('largeFile.txt', 'utf8');
    readStream.pipe(res);   
    // 1. Fixe the backpressure issue using pipe() method.
    // 2. pipe() automatically write the chunks of data to response. So we don't need to write the data manually using res.write().
});

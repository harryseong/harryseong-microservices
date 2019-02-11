package com.harryseong.microservices.controller;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import com.harryseong.microservices.MicroservicesApplication;
import com.harryseong.microservices.domain.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class ApiControllerV1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(MicroservicesApplication.class);

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping( "/greeting")
    public Greeting getGreeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }

    @GetMapping("/speech")
    public Object getSpeech() throws IOException {
        LOGGER.info("Running speech api endpoint.");

        // Instantiates a client
        try (SpeechClient speechClient = SpeechClient.create()) {

            // The path to the audio file to transcribe
            String fileName = "/Users/harry/Desktop/harryseong-microservices/src/main/resources/speech-to-text-test.flac";

            // Reads the audio file into memory
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            // Builds the sync recognize request
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.FLAC)
                    .setSampleRateHertz(44100)
                    .setLanguageCode("en-US")
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            LOGGER.info("Got speech results.");
            LOGGER.info(results.toString());

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                LOGGER.info("Transcription: %s%n", alternative.getTranscript());
            }
            return results.toString();
        } catch (Error e) {
            LOGGER.error("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}

import org.vosk.LibVosk
import org.vosk.LogLevel
import org.vosk.Model
import org.vosk.Recognizer
import java.io.BufferedInputStream
import java.io.FileInputStream
import javax.sound.sampled.AudioSystem


fun defaultModel() = System.getProperty("user.home") + "/.vosk/vosk-model-small-en-us-0.15"

fun main() {
  LibVosk.setLogLevel(LogLevel.DEBUG)

  Model(defaultModel()).use { model ->
    AudioSystem.getAudioInputStream(BufferedInputStream(FileInputStream("test.wav"))).use { ais ->
        Recognizer(model, 16000F).use { recognizer ->
          recognizer.setMaxAlternatives(10)
          recognizer.setWords(true)
          recognizer.setPartialWords(true)
          var nbytes: Int
          val b = ByteArray(4096)
          while (ais.read(b).also { nbytes = it } >= 0) {
            if (recognizer.acceptWaveForm(b, nbytes))
              println(recognizer.result)
            else
              println(recognizer.partialResult)

          }
          println(recognizer.finalResult)
        }
      }
  }
}
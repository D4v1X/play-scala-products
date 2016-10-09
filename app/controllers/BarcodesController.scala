package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{Action, Controller}

/**
  * Created by d4v1x on 09/10/2016.
  */
@Singleton
class BarcodesController @Inject() extends Controller {

  val ImageResolution = 144

  def barcode(ean: String) = Action {

    val mimeType = "image/png"
    try {
      val imageData = ean13BarCode(ean, mimeType)
      Ok(imageData).as(mimeType)
    }
    catch {
      case e: IllegalArgumentException =>
        BadRequest("Could not generate bar code. Error: " + e.getMessage)
    }
  }

  def ean13BarCode(ean: String, mimeType: String): Array[Byte] = {

    import java.io.ByteArrayOutputStream
    import java.awt.image.BufferedImage
    import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
    import org.krysalis.barcode4j.impl.upcean.EAN13Bean


    val output = new ByteArrayOutputStream
    val canvas = new BitmapCanvasProvider(output, mimeType, ImageResolution, BufferedImage.TYPE_BYTE_BINARY, false, 0)
    val barcode = new EAN13Bean

    barcode.generateBarcode(canvas, ean)
    canvas.finish()
    output.toByteArray
    val result = output.toByteArray
    output.close()
    result
  }
}

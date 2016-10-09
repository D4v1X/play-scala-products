package controllers

import javax.inject.{Inject, Singleton}

import models.Product
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.{Action, Controller, Flash}

/**
  * Created by d4v1x on 09/10/2016.
  */
@Singleton
class ProductsController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  private val productForm: Form[Product] = Form(
    mapping(
      "ean" -> nonEmptyText.verifying("Doh - Product already exists!", Product.findByEan(_).isEmpty),
      "name" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Product.apply)(Product.unapply)
  )

  val foo = productForm.copy()


  def list = Action { implicit request =>

    val products = Product.findAll

    Ok(views.html.products.list(products))
  }

  def show(ean: String) = Action { implicit request =>

    Product.findByEan(ean).map { product =>

      Ok(views.html.products.details(product))

    }.getOrElse(

      NotFound

    )
  }

  def newProduct = Action { implicit request =>
    val form = if (request.flash.get("error").isDefined)
      productForm.bind(request.flash.data)
    else
      productForm

    Ok(views.html.products.editProduct(form))
  }

  def save = Action { implicit request =>
    val newProductForm = productForm.bindFromRequest()

    newProductForm.fold(
      hasErrors = { form =>
        Redirect(routes.ProductsController.newProduct()).
          flashing(Flash(form.data) +
            ("error" -> Messages("validation.errors")))
      },
      success = { newProduct =>
        Product.add(newProduct)
        val message = Messages("products.new.success", newProduct.name)
        Redirect(routes.ProductsController.show(newProduct.ean)).
          flashing("success" -> message)
      }
    )
  }




}

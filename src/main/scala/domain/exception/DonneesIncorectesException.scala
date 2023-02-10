package domain.exception

final case class DonneesIncorectesException(final val message: String = "Données d'entrée incorrects",
                                            final val cause: Throwable = None.orNull) extends Exception(message, cause){}

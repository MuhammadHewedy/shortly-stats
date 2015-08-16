
import java.util.Date

import org.parse4j._

object Main extends App{

  Parse.initialize("S0ryVHKLgL3MII7OmnIRSvzQkCkAAMtvc6BrCKQS", "iG1oZ8OTNBpIJeLzOz6Oj8sS4y5c2J6Pbd6hEsH2")

  val xs = Util.finalAll("URLMapping")

  println("xs length: " + xs.length)


  def groupBy[T](xs: Stream[ParseObject]) (g: ParseObject => T): Stream[(T, Int)] = {
    (xs groupBy g).toStream.map(p => (p._1, p._2.length))
  }

  Util.prettyPrint(groupBy (xs) (o => Util.byMonth(o.getCreatedAt)) sortBy (_._1))

  Util.prettyPrint(groupBy (xs) (_.get("source")))

  Util.prettyPrint(groupBy (xs) (_.get("owner_id")) filterNot(_._2 == 1) sortBy(_._2))

}
